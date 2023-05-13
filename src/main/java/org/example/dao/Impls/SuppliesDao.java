package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Supplies;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppliesDao  implements dao {

    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public SuppliesDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Supplies supplies = (Supplies) entity;
        String sql = "INSERT INTO Supplies (sup_producer, sup_book, sup_price, sup_data) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, supplies.getProducerId());
            statement.setInt(2, supplies.getBookId());
            statement.setInt(3, supplies.getPrice());
            statement.setDate(4, supplies.getDate());
            statement.executeUpdate();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    supplies.setId(keys.getLong(1));
                }
            }
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> supplies = new ArrayList<>();
        String sql = "SELECT sup_id, sup_producer, sup_book, sup_price, sup_data FROM Supplies";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                supplies.add(new Supplies(resultSet.getLong("sup_id"),
                        resultSet.getInt("sup_producer"),
                        resultSet.getInt("sup_book"),
                        resultSet.getInt("sup_price"),
                        resultSet.getDate("sup_data")));
            }
        }
        return supplies;
    }

    public Supplies getById(Long id) throws SQLException {
        String sql = "SELECT sup_producer, sup_book, sup_price, sup_data FROM Supplies WHERE sup_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Supplies supplies = new Supplies(id,
                            resultSet.getInt("sup_producer"),
                            resultSet.getInt("sup_book"),
                            resultSet.getInt("sup_price"),
                            resultSet.getDate("sup_data"));
                    return supplies;
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        Supplies supplies = (Supplies) entity;
        String sql = "UPDATE Supplies SET sup_producer=?, sup_book=?, sup_price=?, sup_data=? WHERE sup_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, supplies.getProducerId());
            statement.setInt(2, supplies.getBookId());
            statement.setInt(3, supplies.getPrice());
            statement.setDate(4, supplies.getDate());
            statement.setLong(5, supplies.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Supplies WHERE sup_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}