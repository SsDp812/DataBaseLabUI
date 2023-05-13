package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class Genre extends Entity {
    private Long id;
    private String name;
    private String description;

    public Genre(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

        countOFCols = 3;
        colNames = new String[]{"id","name","decription"};
    }


    public static  Entity getEntityFromList(List<String> list){
        return new Genre(Long.parseLong(list.get(0)),list.get(1),list.get(2));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}