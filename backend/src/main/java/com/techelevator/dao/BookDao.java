package com.techelevator.dao;

import com.techelevator.model.Book;

public interface BookDao {

    Book getBookByAuthor(String author);

    Book getBookByTitle(String title);

    Book getBookByISBN(int isbn);




}
