package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.User;

import java.util.List;

public interface BookDao {

    Book getBookByAuthor(String author);

    Book getBookByTitle(String title);

    Book getBookByISBN(int isbn);

    void createBook(Book book);

    List<Book> getBooks(Long UserId);




}
