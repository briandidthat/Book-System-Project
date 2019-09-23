package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="note-service")
public interface NoteClient {
    @RequestMapping(value="/notes", method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note);

    @RequestMapping(value="/notes", method = RequestMethod.GET)
    public List<Note> getAllNotes();

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public Note getNote(@PathVariable int id);

    @RequestMapping(value="/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable int id, @RequestBody @Valid Note note);

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable int id);

    @RequestMapping(value="/notes/book/{bookId}", method = RequestMethod.GET)
    public List<Note> getNotesByBookId(@PathVariable int bookId);

}
