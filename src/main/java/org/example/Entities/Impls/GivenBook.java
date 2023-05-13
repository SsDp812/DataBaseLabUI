package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class GivenBook extends Entity {
    private Long id;
    private Date date;
    private int days;
    private boolean status;
    private int userId;
    private int bookId;

    public GivenBook(Long id, Date date, int days, boolean status, int userId, int bookId) {
        this.id = id;
        this.date = date;
        this.days = days;
        this.status = status;
        this.userId = userId;
        this.bookId = bookId;

        countOFCols = 6;
        colNames = new String[]{"id","date","days","status","userId","bookId"};
    }




    public static Entity getEntityFromList(List<String> list) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return new GivenBook(Long.parseLong(list.get(0)),(Date) formatter.parse(list.get(1)),Integer.parseInt(list.get(2)),
                Boolean.getBoolean(list.get(3)),Integer.parseInt(list.get(4)),Integer.parseInt(list.get(5)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "GivenBook{" +
                "id=" + id +
                ", date=" + date +
                ", days=" + days +
                ", status=" + status +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}