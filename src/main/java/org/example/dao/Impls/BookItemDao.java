package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.BookItem;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookItemDao  implements dao {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public BookItemDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        BookItem bookItem = (BookItem) entity;
        String query = "INSERT INTO BookItem (book, inventoryNumber) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, bookItem.getId());
        preparedStatement.setInt(2, bookItem.getInventoryNumber());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public BookItem getById(Long id) throws SQLException {
        String query = "SELECT * FROM BookItem WHERE itemId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        BookItem bookItem = null;
        if (resultSet.next()) {
            bookItem = new BookItem(
                    resultSet.getLong(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
            );
        }
        preparedStatement.close();
        return bookItem;
    }

    public void update(Entity entity) throws SQLException {
        BookItem bookItem = (BookItem) entity;
        String query = "UPDATE BookItem SET book = ?, inventoryNumber = ? WHERE itemId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, bookItem.getId());
        preparedStatement.setInt(2, bookItem.getInventoryNumber());
        preparedStatement.setInt(3, bookItem.getInventoryNumber());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(Long id) throws SQLException  {
        String query = "DELETE FROM BookItem WHERE itemId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> bookItems = new ArrayList<>();
        String query = "SELECT * FROM BookItem";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            bookItems.add(new BookItem(
                    resultSet.getLong(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)
            ));
        }
        preparedStatement.close();
        return bookItems;
    }
}