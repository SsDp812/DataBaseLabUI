package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Author;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements dao {
    private Connection connection;


    public void closeConnection() throws SQLException {
        connection.close();
    }

    public AuthorDao(Connection connection) {
        this.connection = connection;
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> authors = new ArrayList<>();
        String sql = "SELECT * FROM Author";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("author_id");
                String name = resultSet.getString("author_name");
                String surname = resultSet.getString("author_surname");
                String dadname = resultSet.getString("author_dadname");
                int year = resultSet.getInt("author_year");
                Author author = new Author(id, name, surname, dadname, year);
                authors.add(author);
            }
        }
        return authors;
    }

    public Author getById(Long id) throws SQLException {
        String sql = "SELECT * FROM Author WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("author_name");
                String surname = resultSet.getString("author_surname");
                String dadname = resultSet.getString("author_dadname");
                int year = resultSet.getInt("author_year");
                return new Author(id, name, surname, dadname, year);
            } else {
                throw new SQLException("No author with id " + id + " found");
            }
        }
    }

    public void create(Entity entity) throws SQLException {
        String sql = "INSERT INTO Author (author_name, author_surname, author_dadname, author_year) VALUES (?, ?, ?, ?)";
        Author author = (Author) entity;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            statement.setString(3, author.getDadname());
            statement.setInt(4, author.getYear());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    Long id = generatedKeys.getLong(1);
                    author.setId(id);
                } else {
                    throw new SQLException("Creating author failed, no ID obtained");
                }
            } else {
                throw new SQLException("Creating author failed, no rows affected");
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        Author author = (Author) entity;
        String sql = "UPDATE Author SET author_name = ?, author_surname = ?, author_dadname = ?, author_year = ? WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            statement.setString(3, author.getDadname());
            statement.setInt(4, author.getYear());
            statement.setLong(5, author.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Updating author failed, no rows affected");
            }
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Author WHERE author_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Deleting author failed, no rows affected");
            }
        }
    }
}