package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Producer;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProducerDao  implements dao {

    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public ProducerDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Producer producer = (Producer) entity;
        String sql = "INSERT INTO Producer (producer_name, producer_city) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producer.getName());
            statement.setString(2, producer.getCity());
            statement.executeUpdate();
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> producers = new ArrayList<>();
        String sql = "SELECT producer_id, producer_name, producer_city FROM Producer";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                producers.add(new Producer(resultSet.getLong("producer_id"),
                        resultSet.getString("producer_name"),
                        resultSet.getString("producer_city")));
            }
        }
        return producers;
    }

    public Producer getById(Long id) throws SQLException {
        String sql = "SELECT producer_id, producer_name, producer_city FROM Producer WHERE producer_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Producer producer = new Producer(resultSet.getLong("producer_id"),
                            resultSet.getString("producer_name"),
                            resultSet.getString("producer_city"));
                    return producer;
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        Producer producer = (Producer) entity;
        String sql = "UPDATE Producer SET producer_name=?, producer_city=? WHERE producer_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producer.getName());
            statement.setString(2, producer.getCity());
            statement.setLong(3, producer.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Producer WHERE producer_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}