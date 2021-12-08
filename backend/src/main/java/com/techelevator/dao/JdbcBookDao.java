package com.techelevator.dao;

import com.techelevator.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcBookDao implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getBookByAuthor(String author) {
        return null;
    }

    @Override
    public Book getBookByTitle(String title) {
        return null;
    }

    @Override
    public Book getBookByISBN(int isbn) {
        return null;
    }

    @Override
    public void createBook(Book book) {
        System.out.println(book.getTitle());

        String sql = "INSERT INTO books (isbn, title, author, thumbnail, page_count, description) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthor(),
                book.getThumbnail(), book.getPageCount(), book.getDescription());

    }
}
