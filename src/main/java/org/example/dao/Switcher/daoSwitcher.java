package org.example.dao.Switcher;

import org.example.Connections.ConnectionMaker;
import org.example.dao.Impls.*;
import org.example.dao.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class daoSwitcher {
    static public dao getDao(String tableName) throws Exception {
        return switch (tableName) {
            case "author" -> new AuthorDao(ConnectionMaker.getConnect());
            case "book" -> new BookDao(ConnectionMaker.getConnect());
            case "bookitem" -> new BookItemDao(ConnectionMaker.getConnect());
            case "booksandauthors" -> new BooksAndAuthorsDao(ConnectionMaker.getConnect());
            case "debtors" -> new DebtorsDao(ConnectionMaker.getConnect());
            case "documents" -> new DocumentDao(ConnectionMaker.getConnect());
            case "genre" -> new GenreDao(ConnectionMaker.getConnect());
            case "givenbook" -> new GivenBookDao(ConnectionMaker.getConnect());
            case "producer" -> new ProducerDao(ConnectionMaker.getConnect());
            case "publisher" -> new PublisherDao(ConnectionMaker.getConnect());
            case "supplies" -> new SuppliesDao(ConnectionMaker.getConnect());
            case "supplogs" -> new SuppLogsDao(ConnectionMaker.getConnect());
            case "users" -> new UserDao(ConnectionMaker.getConnect());
            default -> throw new Exception("error tableName");
        };
    }
}
