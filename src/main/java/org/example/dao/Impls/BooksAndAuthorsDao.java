package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.BooksAndAuthors;
import org.example.Entities.Impls.Debtor;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksAndAuthorsDao  implements dao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public BooksAndAuthorsDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        BooksAndAuthors booksAndAuthors = (BooksAndAuthors) entity;
        String sql = "INSERT INTO BooksAndAuthors (book, author) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, booksAndAuthors.getBookId());
            statement.setInt(2, booksAndAuthors.getAuthorId());
            statement.executeUpdate();
        }
    }


    public List<Entity> getAll() throws SQLException {
        List<Entity> bookslist = new ArrayList<>();
        String query = "SELECT * FROM booksandauthors";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            bookslist.add(new BooksAndAuthors(
                    resultSet.getInt(1),
                    resultSet.getInt(2)
            ));
        }
        preparedStatement.close();
        return bookslist;
    }

    @Override
    public void update(Entity entity) throws SQLException {

    }

    public List<Integer> getAuthorsByBook(int bookId) throws SQLException {
        List<Integer> authors = new ArrayList<>();
        String sql = "SELECT author FROM BooksAndAuthors WHERE book=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    authors.add(resultSet.getInt("author"));
                }
            }
        }
        return authors;
    }

    public void delete(Long bookId) throws SQLException {
        String sql = "DELETE FROM BooksAndAuthors WHERE book=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, bookId);
            statement.executeUpdate();
        }
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        return null;
    }

}