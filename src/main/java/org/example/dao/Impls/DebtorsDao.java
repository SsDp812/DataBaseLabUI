package org.example.dao.Impls;

import org.example.Entities.Entity;
import org.example.Entities.Impls.Debtor;
import org.example.dao.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DebtorsDao  implements dao {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public DebtorsDao(Connection connection) {
        this.connection = connection;
    }

    public void create(Entity entity) throws SQLException {
        Debtor debtors = (Debtor) entity;
        String query = "INSERT INTO Debtors (givenBook, overdueDays, fine) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, debtors.getId());
        preparedStatement.setInt(2, debtors.getOverdueDays());
        preparedStatement.setInt(3, debtors.getFine());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public Debtor getById(Long id) throws SQLException {
        String query = "SELECT * FROM Debtors WHERE deb_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Debtor debtors = null;
        if (resultSet.next()) {
            debtors = new Debtor(
                    resultSet.getLong(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
        }
        preparedStatement.close();
        return debtors;
    }

    public void update(Entity entity) throws SQLException {
        Debtor debtors = (Debtor) entity;
        String query = "UPDATE Debtors SET givenBook = ?, overdueDays = ?, fine = ? WHERE deb_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, debtors.getGivenBook());
        preparedStatement.setInt(2, debtors.getOverdueDays());
        preparedStatement.setInt(3, debtors.getFine());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void delete(Long id) throws SQLException  {
        String query = "DELETE FROM Debtors WHERE deb_id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Entity> getAll() throws SQLException {
        List<Entity> debtorsList = new ArrayList<>();
        String query = "SELECT * FROM Debtors";
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            debtorsList.add(new Debtor(
                    resultSet.getLong(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            ));
        }
        preparedStatement.close();
        return debtorsList;
    }
}