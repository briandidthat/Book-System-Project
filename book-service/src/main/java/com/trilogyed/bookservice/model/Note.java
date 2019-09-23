package com.trilogyed.bookservice.model;

import java.util.Objects;

/*Task:
 *  Will Map the note to the note in the DB
 *  Will be cached, so we use
 */
public class Note {
    private int noteId;
    private int bookId;
    private String note;

    public Note(int bookId, String note) {
        this.bookId = bookId;
        this.note = note;
    }

    public Note() {
    }

    // SETTERS
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // GETTERS
    public int getNoteId() {
        return noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getNote() {
        return note;
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

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }
}
