package com.techelevator.controller;

import com.techelevator.dao.JdbcBookDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.support.JdbcBeanDefinitionReader;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {

    private JdbcBookDao dao;
    private JdbcUserDao userDao;

    public BookController(JdbcBookDao dao, JdbcUserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/postBook", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book, Principal user) {
        User currentUser = userDao.findByUsername(user.getName());
        try {
            dao.createBook(book, currentUser.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value= "/getBookCollection", method = RequestMethod.GET)
    public List<Book> getBooksCollection(Principal user) {
        User currentUser = userDao.findByUsername(user.getName());
        return dao.getBooks(currentUser.getId());
    }



}
