package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class Author extends Entity {
    private Long id;
    private String name;
    private String surname;
    private String dadname;
    private int year;

    public Author(Long id, String name, String surname, String dadname, int year) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dadname = dadname;
        this.year = year;
        countOFCols = 5;
        colNames = new String[]{"id","name","surname","dadname","year"};
    }

    public static Author getEntityFromList(List<String> list){
        return new Author(Long.parseLong(list.get(0)),list.get(1),list.get(2),list.get(3),Integer.parseInt(list.get(4)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDadname() {
        return dadname;
    }

    public void setDadname(String dadname) {
        this.dadname = dadname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dadname='" + dadname + '\'' +
                ", year=" + year +
                '}';
    }
}