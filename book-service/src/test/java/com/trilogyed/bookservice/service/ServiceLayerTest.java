package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.dao.BookDaoJdbcTemplateImpl;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteClient;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ServiceLayerTest {

    BookService service;
    BookDao bookDao;
    NoteClient noteClient;

    @Before
    public void setUp() throws Exception{
        setUpBookDaoMock();
        setUpNoteClientMock();

        service = new BookService(bookDao, noteClient);
    }

    @Test
    public void saveBook(){
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(45);
        bvm.setTitle("The Gadfly");
        bvm.setAuthor("Ethel Lilian Voynich");

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(45);
        note.setNote("Some note about a book");
        List<Note> notes = new ArrayList<>();
        notes.add(note);

        bvm.setNoteList(notes);


        Book book = new Book();
        book.setTitle("The Gadfly");
        book.setAuthor("Ethel Lilian Voynich");

        BookViewModel fromService = service.saveBook(book);

        assertEquals(bvm, fromService);


    }

    @Test
    public void findBookById(){
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(45);
        bvm.setTitle("The Gadfly");
        bvm.setAuthor("Ethel Lilian Voynich");

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(45);
        note.setNote("Some note about a book");
        List<Note> notes = new ArrayList<>();
        notes.add(note);

        bvm.setNoteList(notes);

        BookViewModel fromService = service.findBookById(45);

        assertEquals(fromService, bvm);

    }

    @Test
    public void getBooks(){
        List<BookViewModel> fromService = service.getBooks();
        assertEquals(fromService.size(), 1);
    }

    @Test
    public void updateBook(){
        Book bookUpdate = new Book();
        bookUpdate.setBookId(1);
        bookUpdate.setTitle("Title Updated");
        bookUpdate.setAuthor("Author Updated");

        service.updateBook(bookUpdate);

        Book book = bookDao.getBook(1);

        assertEquals(bookUpdate, book);

    }


    @Test
    public void removeBook(){
        service.deleteBook(3);
        BookViewModel bvm = service.findBookById(3);
        assertNull(bvm);
    }



    //mocks
    public void setUpBookDaoMock(){
        //create a mock object for BookDaoJdbcTemplateImpl
        bookDao = mock(BookDaoJdbcTemplateImpl.class);

        //mock the add
        Book book = new Book();
        book.setBookId(45);
        book.setTitle("The Gadfly");
        book.setAuthor("Ethel Lilian Voynich");

        Book book2 = new Book();
        book2.setTitle("The Gadfly");
        book2.setAuthor("Ethel Lilian Voynich");

        List<Book> books = new ArrayList<>();
        books.add(book);

        doReturn(book).when(bookDao).addBook(book2);
        doReturn(book).when(bookDao).getBook(45);
        doReturn(books).when(bookDao).getAllBooks();


        //mock update
        Book bookUpdate = new Book();
        bookUpdate.setBookId(1);
        bookUpdate.setTitle("Title Updated");
        bookUpdate.setAuthor("Author Updated");

        doNothing().when(bookDao).updateBook(bookUpdate);
        doReturn(bookUpdate).when(bookDao).getBook(1);

        //mock delete
        Book bookDelete = new Book();
        bookDelete.setBookId(3);
        bookDelete.setTitle("Book forDeletion");
        bookDelete.setAuthor("Author forDeletion");

        doNothing().when(bookDao).deleteBook(3);
        doReturn(null).when(bookDao).getBook(3);

    }


    public void setUpNoteClientMock(){
        noteClient = mock(NoteClient.class);

        //Mock the add
        Note note1 = new Note();
        note1.setBookId(45);
        note1.setNote("Some note about a book");

        Note note = new Note();
        note.setNoteId(1);
        note.setBookId(45);
        note.setNote("Some note about a book");

        List<Note> notes = new ArrayList<>();
        notes.add(note);

        doReturn(note).when(noteClient).createNote(note1);
        doReturn(note).when(noteClient).getNote(1);
        doReturn(notes).when(noteClient).getAllNotes();
        doReturn(notes).when(noteClient).getNotesByBookId(45);

        //mock the update
        Note noteUpdate = new Note();

        noteUpdate.setNoteId(2);
        noteUpdate.setBookId(45);
        noteUpdate.setNote("Update notes about a book");

        //need to check this
        doNothing().when(noteClient).updateNote(note.getNoteId(), note);
        doReturn(noteUpdate).when(noteClient).getNote(2);

        //mock the delete

        Note noteDelete = new Note();
        noteDelete.setNoteId(3);
        noteDelete.setBookId(45);
        noteDelete.setNote("Note for deletion");

        doNothing().when(noteClient).deleteNote(3);
        doReturn(null).when(noteClient).getNote(3);

    }



















}




