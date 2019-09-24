package com.trilogyed.notequeueconsumer.util.messages;

/*Task:
 *  Will serve as the intermediate class to receive messages from the producer
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
