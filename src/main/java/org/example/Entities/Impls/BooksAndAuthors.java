package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class BooksAndAuthors extends Entity {
    private int bookId;
    private int authorId;

    public BooksAndAuthors(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;

        countOFCols = 2;
        colNames = new String[]{"bookId","authorId"};
    }

    public static  Entity getEntityFromList(List<String> list){
        return new BooksAndAuthors(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1)));
    }
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BooksAndAuthors{" +
                "bookId=" + bookId +
                ", authorId=" + authorId +
                '}';
    }
}