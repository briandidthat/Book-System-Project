package com.trilogyed.noteservice.serviceTest;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.dao.NoteDaoJdbcTemplateImpl;
import com.trilogyed.noteservice.model.Note;
import com.trilogyed.noteservice.service.NoteService;
import com.trilogyed.noteservice.viewmodel.NoteViewModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class NoteServiceTest {

    NoteDao noteDao;

    //service Layer
    NoteService service;

    @Before
    public void setUp() throws Exception{

        setUpNoteDaoMock();

        service= new NoteService(noteDao);
    }

    /***************************************Note Mock Test *******************************************/

    @Test
    public void saveNote(){

        NoteViewModel noteViewModel = new NoteViewModel();

        //adding the first Note form the save Note DAO without the Id


        noteViewModel.setBookId(11);
        noteViewModel.setNote("first note");

        noteViewModel=service.saveNote(noteViewModel);

        NoteViewModel fromService=service.findNote(noteViewModel.getNoteId());

        assertEquals(noteViewModel,fromService);
    }

    @Test
    public void findNote(){
        //taking the first Note from saveNoteDaoMock

        Note note = new Note();

        note.setNoteId(1);
        note.setBookId(11);
        note.setNote("first note");

        //adding into the NoteViewModel
        NoteViewModel noteViewModel = service.findNote(1);

        //to pass the noteViewModel to compare

        Note note1 = new Note();

        note1.setNoteId(noteViewModel.getNoteId());
        note1.setBookId(noteViewModel.getBookId());
        note1.setNote(noteViewModel.getNote());

        assertEquals(note1,note);
    }

    @Test
    public void findAllNotes(){

        //getting all the notes from NoteViewModel

        List<NoteViewModel> fromService= service.findAllNotes();

        assertEquals(1,fromService.size());
    }

    @Test
    public void updateNote(){

        NoteViewModel noteViewUpdate = new NoteViewModel();

        //taking the Note from the Update chunk of DAOMock

        Note updateNote= new Note();

        updateNote.setNoteId(2);
        updateNote.setBookId(22);
        updateNote.setNote("first note updated");

        //passing the note into the NoteViewModel

        noteViewUpdate.setNoteId(updateNote.getNoteId());
        noteViewUpdate.setBookId(updateNote.getBookId());
        noteViewUpdate.setNote(updateNote.getNote());

        service.updateNote(noteViewUpdate);

        //find the note we just updated
        NoteViewModel noteViewModel = service.findNote(2);

        //passing the updated note into another Note object

        Note noteAfterUpdate= new Note();

        noteAfterUpdate.setNoteId(noteViewModel.getNoteId());
        noteAfterUpdate.setBookId(noteViewModel.getBookId());
        noteAfterUpdate.setNote(noteViewModel.getNote());

        //comparing both note

        assertEquals(noteAfterUpdate,updateNote);
    }

    @Test
    public void removeNote(){

        //taking the id from removeNoteDaoMock
        service.removeNote(3);

        NoteViewModel noteViewModel = service.findNote(3);
        assertNull(noteViewModel);
    }

    @Test
    public void getNotesByBookId(){

        NoteViewModel noteViewModel = new NoteViewModel();

        //adding the first Note from the save NoteDaoMock

        noteViewModel.setNoteId(1);
        noteViewModel.setBookId(11);
        noteViewModel.setNote("first note");

        noteViewModel= service.saveNote(noteViewModel);

        List<NoteViewModel> byBookId=service.findNotesByBook(11);
        assertEquals(1,byBookId.size());

        byBookId=service.findNotesByBook(99); // it's fake id

        assertEquals(0,byBookId.size());
        
    }

    /********************************************DAO MOCK*****************************************/
    /********************************************DAO MOCK*****************************************/

    private void setUpNoteDaoMock(){

        //creating a mock object for NoteDaoJdbcTemplateImpl

        noteDao=mock(NoteDaoJdbcTemplateImpl.class);

        //Mock the add Note

        Note note = new Note();

        note.setNoteId(1);
        note.setBookId(11);
        note.setNote("first note");

        //creating another Note object with the same data without the id

        Note note1 = new Note();

        note1.setBookId(11);
        note1.setNote("first note");

        doReturn(note).when(noteDao).addNote(note1);

        //Mock the get Note
        doReturn(note).when(noteDao).getNote(1);

        //Mock the getAllNote
        List<Note> noteList = new ArrayList<>();

        noteList.add(note);

        doReturn(noteList).when(noteDao).getAllNotes();

        //Mock the update Note

        Note updateNote= new Note();

        updateNote.setNoteId(2);
        updateNote.setBookId(22);
        updateNote.setNote("first note updated");

        doNothing().when(noteDao).updateNote(updateNote);
        doReturn(updateNote).when(noteDao).getNote(2);

        //Mock the remove Note

        Note deleteNote= new Note();

        deleteNote.setNoteId(3);
        deleteNote.setBookId(33);
        deleteNote.setNote("first note deleted");

        doNothing().when(noteDao).deleteNote(3);
        doReturn(null).when(noteDao).getNote(3);

        //Mock the get Notes By BookId
        doReturn(noteList).when(noteDao).getNotesByBookId(11); // using the save Note Data
    }

    /******************************* End of Note Dao Mock  ******************************/

}


