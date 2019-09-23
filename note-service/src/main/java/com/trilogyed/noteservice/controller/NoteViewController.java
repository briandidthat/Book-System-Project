package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.exception.NotFoundException;
import com.trilogyed.noteservice.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class NoteViewController {

    @Autowired
    NoteDao noteDao;

    @RequestMapping(value="/notes", method= RequestMethod.POST)
    public Note createNote(@RequestBody @Valid Note note){
        noteDao.addNote(note);
        return note;
    }

    @RequestMapping(value="/notes", method=RequestMethod.GET)
    public List<Note> getAllNotes(){
        return noteDao.getAllNotes();
    }

    @RequestMapping(value = "/notes/{id}", method =RequestMethod.GET)
    public Note getNote(@PathVariable int id){
        Note note = noteDao.getNote(id);
        if(note== null)
            throw new NoSuchElementException("Note could not be retrieved for this id");
        return note;
    }

    @RequestMapping(value="/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable int noteId, @RequestBody @Valid Note note){
        if(noteId!=note.getNoteId()) {
            throw new IllegalArgumentException("The Note id in the doesn't match");
        }
        noteDao.updateNote(note);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable int noteId){
        Note note = noteDao.getNote(noteId);

        if(note == null) {
            throw new NotFoundException("No note is available with this id");
        }else {
            noteDao.deleteNote(noteId);
        }
    }

    @RequestMapping(value="/notes/book/{bookId}", method = RequestMethod.GET)
    public List<Note> getNotesByBookId(@PathVariable int bookId){
        List<Note> noteList= noteDao.getNotesByBookId(bookId);
        if(noteList==null)throw new NotFoundException("This Book doesn't have any Note");
        return noteList;
    }
}
