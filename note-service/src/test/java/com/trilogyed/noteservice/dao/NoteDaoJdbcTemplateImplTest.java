package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteDaoJdbcTemplateImplTest {


    @Autowired
    protected NoteDao noteDao;

    @Before
    public void setUp() throws Exception {
        //clear out database
        List<Note> notes = noteDao.getAllNotes();
        notes.stream()
                .forEach(note -> noteDao.deleteNote(note.getNoteId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteNote(){
       // create Note object --> Assemble
        Note note = new Note();
        note.setBookId(2);
        note.setNote("Interesting read!");

        //save to database --> act
        note = noteDao.addNote(note);
        Note note1 = noteDao.getNote(note.getNoteId());

        //Assert
        assertEquals(note, note1);

        noteDao.deleteNote(note.getNoteId());
        note1 = noteDao.getNote(note.getNoteId());
        assertNull(note1);

    }

    @Test
    public void getAllNotes(){

        Note note = new Note();
        note.setBookId(2);
        note.setNote("Interesting read!");
        noteDao.addNote(note);

        note = new Note();
        note.setBookId(5);
        note.setNote("Book about life of Napoleon");
        noteDao.addNote(note);

        List<Note> notes = noteDao.getAllNotes();
        assertEquals(notes.size(), 2);
    }

    @Test
    public void updateBook(){
        Note note = new Note();
        note.setBookId(2);
        note.setNote("Interesting read!");
        note = noteDao.addNote(note);

        note.setBookId(3);
        note.setNote("Autobiography of Bill Clinton");
        noteDao.updateNote(note);

        Note note1 = noteDao.getNote(note.getNoteId());
        assertEquals(note, note1);

    }

    @Test
    public void getNotesByBookId(){

        Note note = new Note();
        note.setBookId(2);
        note.setNote("Interesting read!");
        noteDao.addNote(note);

        note = new Note();
        note.setBookId(5);
        note.setNote("Book about life of Napoleon");
        noteDao.addNote(note);

        note = new Note();
        note.setBookId(5);
        note.setNote("Intersting find!");
        noteDao.addNote(note);

        List<Note> notes = noteDao.getNotesByBookId(5);
        assertEquals(notes.size(), 2);

        List<Note> notes1 = noteDao.getNotesByBookId(2);
        assertEquals(notes1.size(), 1);

        List<Note> notes2 = noteDao.getNotesByBookId(3);
        assertEquals(notes2.size(), 0);
    }
}


































