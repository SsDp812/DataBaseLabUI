package org.example.Entities.switcher;

import org.example.Connections.ConnectionMaker;
import org.example.Entities.Entity;
import org.example.Entities.Impls.*;
import org.example.dao.Impls.*;

import java.text.ParseException;
import java.util.List;

public class entitySwitcher {


    public static Entity makeEntity(String entityName, List<String> params) throws Exception {
        return switch (entityName){
            case "author" -> Author.getEntityFromList(params);
            case "book" -> Book.getEntityFromList(params);
            case "bookitem" -> BookItem.getEntityFromList(params);
            case "booksandauthors" -> BooksAndAuthors.getEntityFromList(params);
            case "debtors" -> Debtor.getEntityFromList(params);
            case "documents" -> Document.getEntityFromList(params);
            case "genre" -> Genre.getEntityFromList(params);
            case "givenbook" -> GivenBook.getEntityFromList(params);
            case "producer" -> Producer.getEntityFromList(params);
            case "publisher" -> Publisher.getEntityFromList(params);
            case "supplies" -> Supplies.getEntityFromList(params);
            case "supplogs" -> SuppLogs.getEntityFromList(params);
            case "users" -> User.getEntityFromList(params);
            default -> throw new Exception("error entityName");
        };
    }
}
