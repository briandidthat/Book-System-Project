package com.trilogyed.noteservice.model;

import java.util.Objects;

public class Note {

    private int noteId;
    private int bookId;
    private String note;

    //getters and setters

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    //Equal and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return noteId == note1.noteId &&
                bookId == note1.bookId &&
                note.equals(note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, bookId, note);
    }
}
