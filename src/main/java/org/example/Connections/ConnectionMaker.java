package org.example.Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
    static String URL = "jdbc:postgresql://localhost:5432/Lab8bd";
    static String userName = "postgres";
    static String password = "rootroot";

    static public Connection getConnect() throws SQLException {
        return DriverManager.getConnection(URL,userName,password);
    }
}
