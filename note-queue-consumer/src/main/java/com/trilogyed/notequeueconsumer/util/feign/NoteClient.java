package com.trilogyed.notequeueconsumer.util.feign;

import com.trilogyed.notequeueconsumer.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "note-service")
public interface NoteClient {
    @RequestMapping(value="/notes", method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note);

    @RequestMapping(value="/notes/{id}", method = RequestMethod.PUT)
    public void updateNote(@PathVariable int id, @RequestBody Note note);

}
