package com.trilogyed.notequeueconsumer.util.messages;

/**
 * NoteEntry:
 * Will serve as the intermediate class to receive messages from the producer.
 * Will capture data until we transfer it to the note class to send over the wire.
 */
public class NoteEntry {
    private int noteId;
    private int bookId;
    private String note;

    public NoteEntry() {
    }

    public NoteEntry(int bookId, String note) {
        this.bookId = bookId;
        this.note = note;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public int getNoteId() {
        return noteId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return "NoteEntry{" +
                "noteId=" + noteId +
                ", bookId=" + bookId +
                ", note='" + note + '\'' +
                '}';
    }

}
