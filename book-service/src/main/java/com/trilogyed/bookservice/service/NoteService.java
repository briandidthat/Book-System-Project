package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteService {
    private NoteClient noteClient;

    @Autowired
    public NoteService(NoteClient noteClient) {
        this.noteClient = noteClient;
    }

    public Note findNote(int id) {
        return noteClient.getNote(id);
    }

    public List<Note> findAllNotes() {
        return noteClient.getAllNotes();
    }

    public List<Note> findNotesByBookId(int bookId) {
        return noteClient.getNotesByBookId(bookId);
    }

    public void removeNote(int id) {
        noteClient.deleteNote(id);
    }

}
