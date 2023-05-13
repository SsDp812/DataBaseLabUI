package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class User extends Entity {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String role;

    public User(Long id, String name, String surname, String email, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;

        countOFCols = 5;
        colNames = new String[]{"id","name","surname","email","role"};
    }


    public static Entity getEntityFromList(List<String> list){
        return new User(Long.parseLong(list.get(0)),list.get(1),list.get(2),list.get(3),list.get(4));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}