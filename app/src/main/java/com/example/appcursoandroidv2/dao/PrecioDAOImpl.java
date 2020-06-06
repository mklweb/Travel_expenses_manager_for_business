package com.example.appcursoandroidv2.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.appcursoandroidv2.entidades.Precio;

public class PrecioDAOImpl implements PrecioDAO {

    SQLiteDatabase db;

    private static final String TABLE = "precio";
    private static final String ID = "id";
    private static final String[] COLUMNS = {"dieta_eu", "dieta_resto", "precio_km"};

    public PrecioDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public String getPrecioKm() throws Exception {
        Cursor cursor = db.rawQuery("SELECT precio_km FROM precio", null);
        String precioKm;
        if (cursor.moveToFirst()){
            precioKm = cursor.getString(cursor.getColumnIndex("precio_km"));
        } else {
            throw new Exception("No existe el registro");
        }
        return precioKm;
    }

    @Override
    public String getDietaEu() throws Exception {
        Cursor cursor = db.rawQuery("SELECT dieta_eu FROM precio", null);
        String dietaEu;
        if (cursor.moveToFirst()){
            dietaEu = cursor.getString(cursor.getColumnIndex("dieta_eu"));
        } else {
            throw new Exception("No existe el registro");
        }
        return dietaEu;
    }

    @Override
    public String getDietaRes() throws Exception {
        Cursor cursor = db.rawQuery("SELECT dieta_resto FROM precio", null);
        String dietaRes;
        if (cursor.moveToFirst()){
            dietaRes = cursor.getString(cursor.getColumnIndex("dieta_resto"));
        } else {
            throw new Exception("No existe el registro");
        }
        return dietaRes;
    }

    @Override
    public void modify(Precio precio) throws Exception {
        // Construimos la consulta
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("UPDATE ");
        strBuilder.append(TABLE);
        for (int i = 0, n = COLUMNS.length; i < n; i++) {
            if(i == 0) {
                strBuilder.append(" SET ").append(COLUMNS[i]).append(" = ?");
            } else {
                strBuilder.append(", ").append(COLUMNS[i]).append(" = ?");
            }
        }
        String sql = strBuilder.toString();
        //Compilamos la consulta
        SQLiteStatement pstmt = db.compileStatement(sql);
        //Le pasamos los parámetros
        bindParams(pstmt, precio);
        //Ejecutamos la consulta
        pstmt.execute();
        //Cerrar la consulta compilada;
        pstmt.close();
    }

    private void bindParams(SQLiteStatement pstmt, Precio precio){
        pstmt.bindDouble(1, precio.getDietaEu());
        pstmt.bindDouble(2, precio.getDietaRes());
        pstmt.bindDouble(3, precio.getPrecioKM());
    }
}
