package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class Debtor extends Entity {
    private Long id;
    private int givenBook;
    private int overdueDays;
    private int fine;

    public Debtor(Long id, int givenBook, int overdueDays, int fine) {
        this.id = id;
        this.givenBook = givenBook;
        this.overdueDays = overdueDays;
        this.fine = fine;

        countOFCols = 4;
        colNames = new String[]{"id","givenBook","overdueDays","fine"};
    }


    public static Entity getEntityFromList(List<String> list){
        return new Debtor(Long.parseLong(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)), Integer.parseInt(list.get(3)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGivenBook() {
        return givenBook;
    }

    public void setGivenBook(int givenBook) {
        this.givenBook = givenBook;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "id=" + id +
                ", givenBook=" + givenBook +
                ", overdueDays=" + overdueDays +
                ", fine=" + fine +
                '}';
    }
}