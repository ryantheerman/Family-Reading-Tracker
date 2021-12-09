package com.techelevator.controller;

import com.techelevator.dao.JdbcBookDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.support.JdbcBeanDefinitionReader;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class BookController {

    private JdbcBookDao dao;
    private  JdbcUserDao userDao;

    public BookController(JdbcBookDao dao, JdbcUserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/postBook", method = RequestMethod.POST)
    public void addBook(@RequestBody Book book, Principal user) {
        int currentUser = userDao.findIdByUsername(user.getName());
        System.out.println(currentUser);
        try {
            dao.createBook(book, currentUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
