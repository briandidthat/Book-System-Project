package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookDao;
import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.service.BookService;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Book Controller:
 * Will serve as the main point of contact and will direct all incoming traffic to the appropriate methods
 * Will need to communicate with the service layer, and the note service/queue
 */
@RestController
@CacheConfig(cacheNames = {"books"})
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @CachePut(key = "#result.getBookId()")
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel createBook(@RequestBody @Valid Book book) {
        return bookService.saveBook(book);
    }

    @Cacheable
    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable int id) {
        BookViewModel bvm = bookService.findBookById(id);
        if (bvm == null) {
            throw new NotFoundException("Sorry, we could not find any book with that id.");
        } else {
            return bvm;
        }
    }

    @RequestMapping(value = "/books", method= RequestMethod.GET)
    public List<BookViewModel> getAllBooks() {
        return bookService.getBooks();
    }

    @CacheEvict(key = "#book.getId()")
    @RequestMapping(value="/book/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("id") @RequestBody @Valid Book book) {
        bookService.updateBook(book);
    }

}
