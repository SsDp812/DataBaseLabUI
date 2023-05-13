package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.BooksAndAuthors;
import org.example.Entities.Impls.SuppLogs;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuppLogsDao  implements dao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public SuppLogsDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        SuppLogs log = (SuppLogs) entity;
        String sql = "INSERT INTO SuppLogs (log_text, dayLog, supplier_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, log.getText());
            statement.setDate(2, log.getDayLog());
            statement.setInt(3, log.getSupplierId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    log.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> logsList = new ArrayList<>();
        String query = "SELECT * FROM SuppLogs";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            logsList.add(new SuppLogs(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getInt(4)

            ));
        }
        preparedStatement.close();
        return logsList;
    }

    public SuppLogs getById(Long id) throws SQLException {
        String sql = "SELECT * FROM SuppLogs WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new SuppLogs(
                            resultSet.getLong("id"),
                            resultSet.getString("log_text"),
                            resultSet.getDate("dayLog"),
                            resultSet.getInt("supplier_id")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        SuppLogs log = (SuppLogs) entity;
        String sql = "UPDATE SuppLogs SET log_text=?, dayLog=?, supplier_id=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, log.getText());
            statement.setDate(2, log.getDayLog());
            statement.setInt(3, log.getSupplierId());
            statement.setLong(4, log.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM SuppLogs WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}