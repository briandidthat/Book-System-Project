package com.trilogyed.noteservice.service;

import com.trilogyed.noteservice.dao.NoteDao;
import com.trilogyed.noteservice.model.Note;
import com.trilogyed.noteservice.viewmodel.NoteViewModel;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteService {

    private NoteDao noteDao;

    //constructor

    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Transactional
    public NoteViewModel saveNote(NoteViewModel noteViewModel){

        //without the noteId
        Note note = new Note();

        note.setBookId(noteViewModel.getBookId());
        note.setNote(noteViewModel.getNote());

        note= noteDao.addNote(note);

        noteViewModel.setNoteId(note.getNoteId());

        return noteViewModel;
    }

    public NoteViewModel findNote(int id){

        Note note= noteDao.getNote(id);
        if(note == null)
            return null;
        else
            return buildNoteViewModel(note);
    }

    public List<NoteViewModel> findAllNotes(){

        List<NoteViewModel> noteViewModelList = new ArrayList<>();

        List<Note> noteList = noteDao.getAllNotes();

        for(Note note: noteList){
            noteViewModelList.add(buildNoteViewModel(note));
        }

        return noteViewModelList;
    }

    @Transactional
    public void updateNote(NoteViewModel noteViewModel){

        Note note = new Note();

        note.setNoteId(noteViewModel.getNoteId());
        note.setBookId(noteViewModel.getBookId());
        note.setNote(noteViewModel.getNote());

         noteDao.updateNote(note);

    }

    public void removeNote(int id){
        noteDao.deleteNote(id);
    }

    public List<NoteViewModel>findNotesByBook(int bookId){

        List<Note> noteList = noteDao.getNotesByBookId(bookId);

        List<NoteViewModel> noteViewModelList = new ArrayList<>();

        for(Note note: noteList){
            noteViewModelList.add(buildNoteViewModel(note));
        }
        return noteViewModelList;
    }


    /********************************************* Helper Method *************************************/

    private NoteViewModel buildNoteViewModel(Note note){

        NoteViewModel noteViewModel = new NoteViewModel();

        noteViewModel.setNoteId(note.getNoteId());
        noteViewModel.setBookId(note.getBookId());
        noteViewModel.setNote(note.getNote());
        return noteViewModel;
    }
}
