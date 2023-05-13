package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class Book extends Entity {
    private Long id;
    private int publisherId;
    private int genreId;
    private int year;
    private int isbn;

    public Book(Long id, int publisherId, int genreId, int year, int isbn) {
        this.id = id;
        this.publisherId = publisherId;
        this.genreId = genreId;
        this.year = year;
        this.isbn = isbn;
        countOFCols = 5;
        colNames = new String[]{"id","publisherId","genreId","year","isbn"};
    }

    public static Entity getEntityFromList(List<String> list){
        return new Book(Long.parseLong(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)),
                Integer.parseInt(list.get(3)), Integer.parseInt(list.get(4)));
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", publisherId=" + publisherId +
                ", genreId=" + genreId +
                ", year=" + year +
                ", isbn=" + isbn +
                '}';
    }
}