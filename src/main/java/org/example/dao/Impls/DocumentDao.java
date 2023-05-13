package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Document;
import org.example.dao.dao;
import org.postgresql.gss.GSSOutputStream;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao  implements dao {

    private Connection connection;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public DocumentDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Document document = (Document) entity;
        String sql = "INSERT INTO Documents (name, status) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, document.getName());
            statement.setBoolean(2, document.getStatus());
            statement.executeUpdate();
        }
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> documents = new ArrayList<>();
        String sql = "SELECT * FROM Documents";
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    documents.add(new Document(
                            resultSet.getDate("doc_name"),
                            resultSet.getBoolean("status")
                    ));
                }
            }
        }
        return documents;
    }

    @Override
    public void update(Entity entity) throws SQLException {

    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM Documents WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public Entity getById(Long id) throws SQLException {
        return null;
    }

}