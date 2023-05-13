package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.User;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao  implements dao{
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        User user = (User) entity;
        String query = "INSERT INTO Users (user_name, user_surname, user_email, user_role) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public User getById(Long id) throws SQLException {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        preparedStatement.close();
        return user;
    }

    public void update(Entity entity) throws SQLException {
        User user = (User) entity;
        String query = "UPDATE Users SET user_name = ?, user_surname = ?, user_email = ?, user_role = ? WHERE user_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(Long id) throws SQLException  {
        String query = "DELETE FROM Users WHERE user_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> userList = new ArrayList<>();
        String query = "SELECT * FROM Users";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            userList.add(new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        preparedStatement.close();
        return userList;
    }
}