package com.example.appcursoandroidv2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion {

    private static SQLiteOpenHelper dbHelper = null;
    private static SQLiteDatabase db;

    private Conexion() {
    }

    public static SQLiteDatabase getInstance(Context c) {
        if (dbHelper == null){
            dbHelper = new DBOpenHelper(c);
        }
        db = dbHelper.getWritableDatabase();
        return db;
    }

}
