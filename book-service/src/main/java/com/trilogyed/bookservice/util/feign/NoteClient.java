package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="note-service")
public interface NoteClient {

    @GetMapping(value="/notes")
    public List<Note> getAllNotes();

    @GetMapping(value = "/notes/{id}")
    public Note getNote(@PathVariable int id);

    @DeleteMapping(value = "/notes/{id}")
    public Note deleteNote(@PathVariable int id);

    @GetMapping(value="/notes/book/{bookId}")
    public List<Note> getNotesByBookId(@PathVariable int bookId);

}
