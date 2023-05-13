package org.example.Entities.Impls;

import org.example.Entities.Entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class Document extends Entity {
    private Date name;
    private boolean status;

    public Document(Date name, boolean status) {
        this.name = name;
        this.status = status;

        countOFCols = 2;
        colNames = new String[]{"name","status"};
    }


    public static  Entity getEntityFromList(List<String> list) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return new Document((Date) formatter.parse(list.get(0)),Boolean.getBoolean(list.get(1)));
    }
    public Date getName() {
        return name;
    }

    public void setName(Date name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name=" + name +
                ", status=" + status +
                '}';
    }
}