package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class Publisher extends Entity {
    private Long id;
    private String name;
    private String city;

    public Publisher(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;

        countOFCols = 3;
        colNames = new String[]{"id","name","city"};
    }


    public static  Entity getEntityFromList(List<String> list){
        return new Publisher(Long.parseLong(list.get(0)),list.get(1),list.get(2));
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
