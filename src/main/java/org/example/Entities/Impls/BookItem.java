package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.util.List;

public class BookItem  extends Entity {
    private Long id;
    private int book;
    private int inventoryNumber;

    public BookItem(Long id, int book, int inventoryNumber) {
        this.id = id;
        this.book = book;
        this.inventoryNumber = inventoryNumber;

        countOFCols = 3;
        colNames = new String[]{"id","book","inventoryNumber"};
    }


    public static Entity getEntityFromList(List<String> list){
        return new BookItem(Long.parseLong(list.get(0)), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", book=" + book +
                ", inventoryNumber=" + inventoryNumber +
                '}';
    }
}