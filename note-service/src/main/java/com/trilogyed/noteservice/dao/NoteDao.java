package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;

import java.util.List;


public interface NoteDao {

    //add note
    Note addNote(Note note);

    //get note
    Note getNote(int noteId);

    //getAll note
    List<Note>getAllNotes();

    //update Note
    void updateNote(Note note);

    //delete Note
    void deleteNote(int noteId);

    //get Notes By Book Id
    List<Note> getNotesByBookId(int bookId);


}
