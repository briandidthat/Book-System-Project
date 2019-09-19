package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RestController;

/**
 * Book Controller:
 * Will serve as the main point of contact and will direct all incoming traffic to the appropriate methods
 * Will need to communicate with the service layer, and the note service/queue
 */
@RestController
@CacheConfig(cacheNames = {"books"})
public class BookController {
    @Autowired
    BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
