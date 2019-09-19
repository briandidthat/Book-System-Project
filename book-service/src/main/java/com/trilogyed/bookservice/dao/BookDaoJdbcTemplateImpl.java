package com.trilogyed.bookservice.dao;

import com.trilogyed.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/*Task:
 *  Implementation of bookDao to perform all necessary book operations
 */
public class BookDaoJdbcTemplateImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public Book getBook(int id) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(int id) {

    }
}
