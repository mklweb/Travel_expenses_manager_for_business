package com.example.appcursoandroidv2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    private Date date;

    public DateParser() {

    }

    public DateParser(Date date) {
        this.date = date;
    }

    public long parse(String f) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            date = sdf.parse(f);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public String toText(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public String toText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}

