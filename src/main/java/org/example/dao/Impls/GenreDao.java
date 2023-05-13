package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Genre;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDao  implements dao {

    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public GenreDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Genre genre = (Genre) entity;
        String sql = "INSERT INTO Genre (genre_name, disciption) VALUES (?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getName());
            statement.setString(2, genre.getDescription());
            statement.executeUpdate();
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> genres = new ArrayList<>();
        String sql = "SELECT genre_id, genre_name, disciption FROM Genre";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                genres.add(new Genre(resultSet.getLong("genre_id"),
                        resultSet.getString("genre_name"),
                        resultSet.getString("disciption")));
            }
        }
        return genres;
    }

    public Genre getById(Long id) throws SQLException {
        String sql = "SELECT genre_id, genre_name, discription FROM Genre WHERE genre_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Genre genre = new Genre(resultSet.getLong("genre_id"),
                            resultSet.getString("genre_name"),
                            resultSet.getString("disciption"));
                    return genre;
                } else {
                    return null;
                }
            }
        }
    }

    public void update(Entity entity) throws SQLException {
        Genre genre = (Genre) entity;
        String sql = "UPDATE Genre SET genre_name=?, discription=? WHERE genre_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, genre.getName());
            statement.setString(2, genre.getDescription());
            statement.setLong(3, genre.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Genre WHERE genre_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}