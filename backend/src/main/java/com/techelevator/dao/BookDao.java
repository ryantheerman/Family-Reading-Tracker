package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.User;

import java.util.List;

import java.security.Principal;

public interface BookDao {

    void createBook(Book book, Long id);

    List<Book> getBooks(Long UserId);




}
