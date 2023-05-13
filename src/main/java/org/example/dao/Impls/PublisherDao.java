package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Publisher;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao  implements dao {

    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public PublisherDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Publisher publisher = (Publisher) entity;
        String sql = "INSERT INTO Publisher (push_name, pub_city) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getCity());
            statement.executeUpdate();
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> publishers = new ArrayList<>();
        String sql = "SELECT push_id, push_name, pub_city FROM Publisher";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                publishers.add(new Publisher(resultSet.getLong("push_id"),
                        resultSet.getString("push_name"),
                        resultSet.getString("pub_city")));
            }
        }
        return publishers;
    }

    public Publisher getById(Long id) throws SQLException {
        String sql = "SELECT push_id, push_name, pub_city FROM Publisher WHERE push_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Publisher publisher = new Publisher(resultSet.getLong("push_id"),
                            resultSet.getString("push_name"),
                            resultSet.getString("pub_city"));
                    return publisher;
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        Publisher publisher = (Publisher) entity;
        String sql = "UPDATE Publisher SET push_name=?, pub_city=? WHERE push_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, publisher.getName());
            statement.setString(2, publisher.getCity());
            statement.setLong(3, publisher.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Publisher WHERE push_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}