package com.techelevator.dao;

import com.techelevator.controller.AuthenticationController;
import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcBookDao implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create Book
    // Retrieve all books

    // No need to update a book
    // Don't want to delete books from books table

    @Override
    public void createBook(Book book, Long id) {

        try {
            String sql = "INSERT INTO books (isbn, title, author, thumbnail, page_count, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
            jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthor(),
                    book.getThumbnail(), book.getPageCount(), book.getDescription());

            String sql2 = "INSERT INTO activity (user_id, isbn, date_read) " +
                    "VALUES (?, ?, ?) RETURNING activity_id;";
            Long newActivityId = jdbcTemplate.queryForObject(sql2, Long.class, id, book.getIsbn(), LocalDate.now());
        } catch (Exception e) {
            String sql2 = "INSERT INTO activity (user_id, isbn, date_read) " +
                    "VALUES (?, ?, ?) RETURNING activity_id;";
            Long newActivityId = jdbcTemplate.queryForObject(sql2, Long.class, id, book.getIsbn(), LocalDate.now());
        }
    }


    //get all books by user id
    @Override
    public List<Book> getBooks(Long UserId) {
        List<Book> listOfBooksById = new ArrayList<>();

        String sql = "SELECT books.isbn, title, author, thumbnail, page_count, description " +
                     "FROM books " +
                     "JOIN activity ON activity.isbn = books.isbn " +
                     "WHERE user_id = ? AND minutes_read = 0;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, UserId);
        while(results.next()) {
            listOfBooksById.add(mapRowToBook(results));
        }
        return listOfBooksById;
    }


    private Book mapRowToBook(SqlRowSet rs) {
        Book book = new Book();
        book.setIsbn(rs.getLong("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setThumbnail(rs.getString("thumbnail"));
        book.setPageCount(rs.getInt("page_count"));
        book.setDescription(rs.getString("description"));
        return book;
    }
}
