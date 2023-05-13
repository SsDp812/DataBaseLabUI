package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SuppLogs extends Entity {
    private Long id;
    private String text;
    private Date dayLog;
    private int supplierId;

    public SuppLogs(Long id, String text, Date dayLog, int supplierId) {
        this.id = id;
        this.text = text;
        this.dayLog = dayLog;
        this.supplierId = supplierId;

        countOFCols = 4;
        colNames = new String[]{"id","text","dayLog","supplierId"};
    }


    public static  Entity getEntityFromList(List<String> list) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return new SuppLogs(Long.parseLong(list.get(0)),list.get(1),(Date) formatter.parse(list.get(2)),Integer.parseInt(list.get(3)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDayLog() {
        return dayLog;
    }

    public void setDayLog(Date dayLog) {
        this.dayLog = dayLog;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "SuppLogs{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dayLog=" + dayLog +
                ", supplierId=" + supplierId +
                '}';
    }
}