package org.example.dao;

import org.example.Entities.Entity;
import org.example.Entities.Impls.SuppLogs;
import org.example.Entities.Impls.User;

import java.sql.SQLException;
import java.util.List;

public interface dao {
    public void closeConnection() throws SQLException;
    public Entity getById(Long id) throws SQLException;

    public void create(Entity entity) throws SQLException;

    public List<Entity> getAll() throws SQLException;

    public void update(Entity entity) throws SQLException;

    public void delete(Long id) throws SQLException;


}
