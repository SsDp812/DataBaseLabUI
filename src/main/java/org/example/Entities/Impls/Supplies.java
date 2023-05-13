package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Supplies extends Entity {
    private Long id;
    private int producerId;
    private int bookId;
    private int price;
    private Date data;

    public Supplies(Long id, int producerId, int bookId, int price, Date data) {
        this.id = id;
        this.producerId = producerId;
        this.bookId = bookId;
        this.price = price;
        this.data = data;

        countOFCols = 5;
        colNames = new String[]{"id","producerId","bookId","price","data"};
    }


    public static  Entity getEntityFromList(List<String> list) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return new Supplies(Long.parseLong(list.get(0)),Integer.parseInt(list.get(1)),Integer.parseInt(list.get(2)),Integer.parseInt(list.get(3)),
                (Date) formatter.parse(list.get(4)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return data;
    }

    public void setDate(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Supplies{" +
                "id=" + id +
                ", producerId=" + producerId +
                ", bookId=" + bookId +
                ", price=" + price +
                ", data=" + data +
                '}';
    }
}