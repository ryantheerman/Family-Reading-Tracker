package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.User;

import java.util.List;

import java.security.Principal;

public interface BookDao {

    Book getBookByAuthor(String author);

    Book getBookByTitle(String title);

    Book getBookByISBN(int isbn);

    void createBook(Book book, int id);

    List<Book> getBooks(Long UserId);




}
