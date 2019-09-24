package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.exception.NotFoundException;
import com.trilogyed.bookservice.model.Book;
import com.trilogyed.bookservice.service.BookService;
import com.trilogyed.bookservice.service.NoteService;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Book Controller:
 * Will serve as the main point of contact and will direct all incoming traffic to the appropriate methods
 * Will need to communicate with the service layer, and the note service/queue
 */

@CacheConfig(cacheNames = {"books"})
@RestController
@RefreshScope
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    NoteService noteService;

    @PostMapping
    @CachePut(key = "#result.getBookId()")
    @ResponseStatus(HttpStatus.CREATED)
    public BookViewModel createBook(@RequestBody @Valid Book book) {
        return bookService.saveBook(book);
    }

    @Cacheable
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookViewModel getBook(@PathVariable int id) {
        BookViewModel bvm = bookService.findBookById(id);
        if (bvm == null) {
            throw new NotFoundException("Sorry, we could not find any book with that id.");
        }
        return bvm;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookViewModel> getAllBooks() {
        return bookService.getBooks();
    }

    @CacheEvict(key = "#book.getId()")
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@PathVariable("id") int id, @RequestBody @Valid Book book) {
        if (book.getBookId() == 0) {
            book.setBookId(id);
        }
        if (id != book.getBookId()){
            throw new IllegalArgumentException("Book Id on path must match the ID in the Book object.");
        }
        bookService.updateBook(book);
    }

    @CacheEvict
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
    }

}
