package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJdbcTemplateImplTest {


    @Autowired
    protected BookDao bookDao;

    @Before
    public void setUp() throws Exception {
        //clear out database
        List<Book> books = bookDao.getAllBooks();
        books.stream()
                .forEach(book -> bookDao.deleteBook(book.getBookId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteBook(){
        //create Book object --> assemble

        Book book = new Book();
        book.setTitle("Lord of the flies");
        book.setAuthor("William Golding");

        //save to database --> act
        book = bookDao.addBook(book);
        Book book1 = bookDao.getBook(book.getBookId());

        //Assert
        assertEquals(book, book1);

    }

    @Test
    public void getAllBooks(){

        Book book = new Book();
        book.setTitle("Lord of the flies");
        book.setAuthor("William Golding");

        bookDao.addBook(book);

        book = new Book();
        book.setTitle("Dandelion Wine");
        book.setAuthor("Ray Bradbury");

        bookDao.addBook(book);

        List<Book> books = bookDao.getAllBooks();
        assertEquals(books.size(), 2);

    }

    @Test
    public void updateBook() {

        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Lord of the flies");
        book.setAuthor("William Golding");

        book = bookDao.addBook(book);

        book.setTitle("To the Ends of the Earth");
        bookDao.updateBook(book);

        Book book1 = bookDao.getBook(book.getBookId());
        assertEquals(book, book1);
    }

}