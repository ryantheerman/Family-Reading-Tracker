package com.techelevator.controller;

import com.techelevator.dao.JdbcBookDao;
import com.techelevator.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.support.JdbcBeanDefinitionReader;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BookController {

    private JdbcBookDao dao;

    public BookController(JdbcBookDao dao) {
        this.dao = dao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/postBook", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book) {
        try {
            dao.createBook(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



}
