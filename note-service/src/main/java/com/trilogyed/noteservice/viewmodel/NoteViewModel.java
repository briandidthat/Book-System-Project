package com.trilogyed.noteservice.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class NoteViewModel {

    private int noteId; //PK no validation
    @NotNull(message = "Cannot proceed without the book id")
    private int bookId;
    @Size(min = 2, max = 255,message = "The note has a conflict with the length")
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

    //Equal and Hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteViewModel that = (NoteViewModel) o;
        return noteId == that.noteId &&
                bookId == that.bookId &&
                note.equals(that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, bookId, note);
    }
}
