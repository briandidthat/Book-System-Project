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


    public List<BookViewModel> getBooks() {
        List<Book> bookList = bookDao.getAllBooks();
        List<BookViewModel> bvmList = new ArrayList<>();

        for (Book b: bookList) {
            BookViewModel bvm = buildViewModel(b);
            bvmList.add(bvm);
        }
        return bvmList;
    }


    private BookViewModel buildViewModel(Book book) {
        BookViewModel bvm = new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());

        List<Note> noteList = noteClient.getNotesByBookId(book.getBookId());
        bvm.setNoteList(noteList);

        return bvm;
    }


}
