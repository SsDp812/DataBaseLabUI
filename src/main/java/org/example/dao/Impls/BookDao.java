package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Book;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao implements dao {

    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public BookDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Book book = (Book) entity;
        String sql = "INSERT INTO Book (book_pub, book_genre, book_year, book_isbn) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, book.getPublisherId());
            statement.setInt(2, book.getGenreId());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getIsbn());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    book.setId(keys.getLong(1));
                }
            }
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> books = new ArrayList<>();
        String sql = "SELECT book_id, book_pub, book_genre, book_year, book_isbn FROM Book";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book(resultSet.getLong("book_id"),
                        resultSet.getInt("book_pub"),
                        resultSet.getInt("book_genre"),
                        resultSet.getInt("book_year"),
                        resultSet.getInt("book_isbn")));
            }
        }
        return books;
    }

    public Book getById(Long id) throws SQLException {
        String sql = "SELECT book_pub, book_genre, book_year, book_isbn FROM Book WHERE book_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book(id,
                            resultSet.getInt("book_pub"),
                            resultSet.getInt("book_genre"),
                            resultSet.getInt("book_year"),
                            resultSet.getInt("book_isbn"));
                    return book;
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        Book book = (Book) entity;
        String sql = "UPDATE Book SET book_pub=?, book_genre=?, book_year=?, book_isbn=? WHERE book_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.getPublisherId());
            statement.setInt(2, book.getGenreId());
            statement.setInt(3, book.getYear());
            statement.setInt(4, book.getIsbn());
            statement.setLong(5, book.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Book WHERE book_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}