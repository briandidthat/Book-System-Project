package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;

import java.util.List;

/*Task:
 * Book Dao that will be used to define all of the necessary methods
 * Will be overridden inside the impl class
 */

public interface BookDao {
    Book addBook(Book book);
    Book getBook(int id);

    List<Book> getAllBooks();

    void updateBook(Book book);
    void deleteBook(int id);
}
