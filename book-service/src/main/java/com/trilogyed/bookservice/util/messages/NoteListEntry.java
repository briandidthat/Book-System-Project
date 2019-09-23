package com.trilogyed.bookservice.util.messages;

import java.util.Objects;

/*Task:
 *  WILL SERVE AS THE MESSAGE THAT WE WILL SEND OVER THE WIRE
 */
public class NoteListEntry {
    private int noteId;
    private int bookId;
    private String note;

    public NoteListEntry(int bookId, String note) {
        this.bookId = bookId;
        this.note = note;
    }

    public NoteListEntry() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteListEntry that = (NoteListEntry) o;
        return noteId == that.noteId &&
                bookId == that.bookId &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, bookId, note);
    }

    @Override
    public String toString() {
        return "NoteListEntry{" +
                "noteId=" + noteId +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }
}
