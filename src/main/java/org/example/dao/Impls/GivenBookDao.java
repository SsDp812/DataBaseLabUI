package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.GivenBook;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GivenBookDao  implements dao {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public GivenBookDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        GivenBook givenBook = (GivenBook) entity;
        String query = "INSERT INTO givenbook (date, days, status, user_id, book_id) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, givenBook.getDate());
        preparedStatement.setInt(2, givenBook.getDays());
        preparedStatement.setBoolean(3, givenBook.getStatus());
        preparedStatement.setLong(4, givenBook.getId());
        preparedStatement.setLong(5, givenBook.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public GivenBook getById(Long id) throws SQLException {
        String query = "SELECT * FROM givenbook WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        GivenBook givenBook = null;
        if (resultSet.next()) {
            givenBook = new GivenBook(
                    resultSet.getLong(1),
                    resultSet.getDate(2),
                    resultSet.getInt(3),
                    resultSet.getBoolean(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6)
            );
        }
        preparedStatement.close();
        return givenBook;
    }

    public void update(Entity entity) throws SQLException {
        GivenBook givenBook = (GivenBook) entity;
        String query = "UPDATE givenbook SET date = ?, days = ?, status = ?, user_id = ?, book_id = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1,givenBook.getDate());
        preparedStatement.setInt(2, givenBook.getDays());
        preparedStatement.setBoolean(3, givenBook.getStatus());
        preparedStatement.setLong(4, givenBook.getId());
        preparedStatement.setLong(5, givenBook.getId());
        preparedStatement.setLong(6, givenBook.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM givenbook WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> givenBooks = new ArrayList<>();
        String query = "SELECT * FROM givenbook";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            givenBooks.add(new GivenBook(
                    resultSet.getLong(1),
                    resultSet.getDate(2),
                    resultSet.getInt(3),
                    resultSet.getBoolean(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6)
            ));
        }
        preparedStatement.close();
        return givenBooks;
    }

}