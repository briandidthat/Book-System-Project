package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.service.NoteService;
import com.trilogyed.bookservice.util.messages.NoteEntry;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.queue.add.note.controller";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private NoteService noteService;

    public NoteController(RabbitTemplate rabbitTemplate, NoteService noteService) {
        this.rabbitTemplate = rabbitTemplate;
        this.noteService = noteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNote(@RequestBody NoteEntry noteEntry) {
        // create message to send to email list creation queue
        System.out.println("Sending note...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, noteEntry);
        System.out.println("Message Sent");
    }

    @PutMapping(value = "/{noteId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateNote(@PathVariable int noteId, @RequestBody Note note) {
        NoteEntry noteEntry = new NoteEntry(note.getBookId(), note.getNote());
        noteEntry.setNoteId(noteId);
        System.out.println("Updating note...");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, noteEntry);
        System.out.println("Note updated");
    }

    //  SIMPLE NOTE ROUTES, WILL BE USED INTERNALLY TO PERFORM OPERATIONS THAT DO NOT NEED TO USE THE QUEUE
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getAllNotes() {
        return noteService.findAllNotes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note getNoteById(@PathVariable int id) {
        return noteService.findNote(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(int id) {
        noteService.removeNote(id);
    }

    @GetMapping("/book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Note> getNotesByBookId(@PathVariable int bookId) {
        return noteService.findNotesByBookId(bookId);
    }

}
