package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.model.Note;
import com.trilogyed.bookservice.util.feign.NoteClient;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Book Service Layer
 * Will perform all necessary operations that will pertain to both book, and notes
 * Private methods: BuildBookViewModel(),
 */
@Component
public class BookService {
    private BookDao bookDao;
    private NoteClient noteClient;

    @Autowired
    public BookService(BookDao bookDao, NoteClient noteClient) {
        this.bookDao = bookDao;
        this.noteClient = noteClient;
    }

    public BookViewModel saveBook(Book book) {
        book = bookDao.addBook(book);
        return buildBookViewModel(book);
    }

    public BookViewModel findBookById(int id) {
        Book book = bookDao.getBook(id);
        if (book == null) {
            return null;
        } else {
            return buildBookViewModel(book);
        }
    }

    public List<BookViewModel> getBooks() {
        List<Book> bookList = bookDao.getAllBooks();
        List<BookViewModel> bvmList = new ArrayList<>();

        for (Book b: bookList) {
            BookViewModel bvm = buildBookViewModel(b);
            bvmList.add(bvm);
        }
        return bvmList;
    }

    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    // this will use the queue system
    public void addNote(Note note) {

    }

    private BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());

        List<Note> noteList = noteClient.getNotesByBookId(book.getBookId());
        bvm.setNoteList(noteList);

        return bvm;
    }


}
