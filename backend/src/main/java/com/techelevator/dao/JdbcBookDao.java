package com.techelevator.dao;

import com.techelevator.model.Book;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcBookDao implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcBookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //get all books by user id

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

        String sql = "INSERT INTO books (isbn, title, author, thumbnail, page_count, description) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, book.getIsbn(), book.getTitle(), book.getAuthor(),
                book.getThumbnail(), book.getPageCount(), book.getDescription());

    }

    @Override
    public List<Book> getBooks(Long UserId) {
        List<Book> listOfBooksById = new ArrayList<>();

        String sql = "SELECT books.isbn, title, author, thumbnail, page_count, description " +
                     "FROM books " +
                     "JOIN activity ON activity.isbn = books.isbn " +
                     "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, UserId);
        while(results.next()) {
            listOfBooksById.add(mapRowToBook(results));
        }
        return listOfBooksById;
    }


    private Book mapRowToBook(SqlRowSet rs) {
        Book book = new Book();
        book.setIsbn(rs.getLong("books.isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setThumbnail(rs.getString("thumbnail"));
        book.setPageCount(rs.getInt("page_count"));
        book.setDescription(rs.getString("description"));
        return book;
    }
}
