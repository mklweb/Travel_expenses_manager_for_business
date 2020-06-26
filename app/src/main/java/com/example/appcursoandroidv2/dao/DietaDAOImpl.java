package com.example.appcursoandroidv2.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.utils.DateParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietaDAOImpl implements DietaDAO {

    SQLiteDatabase db;

    /** Para SELECT sin asterisco
     * Si se añade o elimina una columna a la tabla
     * ponerla aquí, en getContentValues() y en getNextCursor*/
    private static final String[] COLUMNS = {
            Constantes.DIETA_FECHA_INI,
            Constantes.DIETA_FECHA_FIN,
            Constantes.DIETA_PAIS,
            Constantes.DIETA_CIUDAD,
            Constantes.DIETA_DIETA,
            Constantes.DIETA_PRO,
            Constantes.DIETA_DEP
    };

    public DietaDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public long add(Dieta dieta) throws Exception {
        return db.insert(Constantes.TABLA_DIETA, null, getContentValues(dieta));
    }

    @Override
    public int remove(String id) throws Exception {
        String[] args = new String[]{id};
        return db.delete(Constantes.TABLA_DIETA, Constantes.DIETA_ID + "=?", args);
    }

    @Override
    public int modify(Dieta dieta) throws Exception {
        String[] args = new String[]{String.valueOf(dieta.getId())};
        return db.update(Constantes.TABLA_DIETA, getContentValues(dieta), Constantes.DIETA_ID + "=?", args);
    }

    @Override
    public Dieta findById(long id) throws Exception {
        String qry = "SELECT * FROM " + Constantes.TABLA_DIETA + " WHERE " + Constantes.DIETA_ID + "=" + id;
        Cursor cursor = db.rawQuery(qry, null);
        return getResultOne(cursor);
    }

    @Override
    public List<Dieta> findAll() throws Exception {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("SELECT ").append(Constantes.DIETA_ID);
        for (int i = 0, n = COLUMNS.length; i < n; i++) {
            strBuilder.append(", ").append(COLUMNS[i]);
        }
        strBuilder.append(" FROM ").append(Constantes.TABLA_DIETA);
        strBuilder.append(" ORDER BY ").append(Constantes.DIETA_FECHA_INI).append(" DESC");
        String sql = strBuilder.toString();
        Cursor cursor = db.rawQuery(sql, null);
        List<Dieta> dietas = getResultList(cursor);
        return dietas;
    }

    public List<Dieta> filtrarDietas(HashMap<String, String> params ) {

        long desdeFecha, hastaFecha;
        double desdeImporte, hastaImporte;

        DateParser dp = new DateParser();

        try {
            String fechaDesdeTxt = params.get("desde fecha");
            desdeFecha = dp.parse(fechaDesdeTxt);
        } catch (Exception e) {
            desdeFecha = 0;
        }
        try {
            String fechaHastaTxt = params.get("hasta fecha");
            hastaFecha = dp.parse(fechaHastaTxt);
        } catch (Exception e) {
            hastaFecha = 0;
        }
        try {
            desdeImporte = Double.parseDouble(params.get("desde importe"));
        } catch (Exception e) {
            desdeImporte = 0;
        }
        try{
            hastaImporte = Double.parseDouble(params.get("hasta importe"));
        } catch (Exception e) {
            hastaImporte = 0;
        }

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("SELECT ").append(Constantes.DIETA_ID);
        for (int i = 0, n = COLUMNS.length; i < n; i++) {
            strBuilder.append(", ").append(COLUMNS[i]);
        }
        strBuilder.append(" FROM ").append(Constantes.TABLA_DIETA);
        strBuilder.append(" WHERE 1=1");
        if(desdeFecha > 0) {
            /** AND (fecha_ini >= desdeFecha OR fecha_fin >= desdeFecha) */;
            strBuilder.append(" AND (")
                    .append(Constantes.DIETA_FECHA_INI)
                    .append(" >= ")
                    .append(desdeFecha)
                    .append(" OR ")
                    .append(Constantes.DIETA_FECHA_FIN)
                    .append(" >= ")
                    .append(desdeFecha)
                    .append(")");
        }
        if(hastaFecha > 0) {
            /** AND (fecha_ini <= hastaFecha OR fecha_fin <= hastaFecha) */
            strBuilder.append(" AND (")
                    .append(Constantes.DIETA_FECHA_INI)
                    .append(" <= ")
                    .append(hastaFecha)
                    .append(" OR ")
                    .append(Constantes.DIETA_FECHA_FIN)
                    .append(" <= ")
                    .append(hastaFecha)
                    .append(")");
        }
        if(desdeImporte > 0) {
            /** AND desdeImporte <= totalDieta */
            strBuilder.append(" AND ")
                    .append(desdeImporte)
                    .append(" <= ")
                    .append("((")
                    .append(Constantes.DIETA_FECHA_FIN)
                    .append(" - ")
                    .append(Constantes.DIETA_FECHA_INI)
                    .append(") + 24 * 3600 * 1000) / (24 * 3600 * 1000) * ")
                    .append(Constantes.DIETA_DIETA);
        }
        if(hastaImporte > 0) {
            /** AND hastaImporte >= totalDieta */
            strBuilder.append(" AND ")
                    .append(hastaImporte)
                    .append(" >= ")
                    .append("((")
                    .append(Constantes.DIETA_FECHA_FIN)
                    .append(" - ")
                    .append(Constantes.DIETA_FECHA_INI)
                    .append(") + 24 * 3600 * 1000) / (24 * 3600 * 1000) * ")
                    .append(Constantes.DIETA_DIETA);
        }
        strBuilder.append(" ORDER BY ").append(Constantes.DIETA_FECHA_INI).append(" ASC");
        String sql = strBuilder.toString();
        Log.d("sql => ", sql);
        Cursor cursor = db.rawQuery(sql, null);
        List<Dieta> dietas = getResultList(cursor);
        return dietas;
    }

    private ContentValues getContentValues(Dieta dieta) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.DIETA_FECHA_INI, dieta.getFechaIni());
        contentValues.put(Constantes.DIETA_FECHA_FIN, dieta.getFechaFin());
        contentValues.put(Constantes.DIETA_PAIS, dieta.getPais());
        contentValues.put(Constantes.DIETA_CIUDAD, dieta.getCiudad());
        contentValues.put(Constantes.DIETA_DIETA, dieta.getDieta());
        contentValues.put(Constantes.DIETA_PRO, dieta.getProyect());
        contentValues.put(Constantes.DIETA_DEP, dieta.getDepartment());
        return contentValues;
    }

    private Dieta getNextCursor(Cursor cursor, Dieta dieta) {
        dieta.setId( cursor.getLong(cursor.getColumnIndex(Constantes.DIETA_ID) ) );
        dieta.setFechaIni( cursor.getLong(cursor.getColumnIndex(Constantes.DIETA_FECHA_INI) ) );
        dieta.setFechaFin( cursor.getLong(cursor.getColumnIndex(Constantes.DIETA_FECHA_FIN) ) );
        dieta.setPais( cursor.getString(cursor.getColumnIndex(Constantes.DIETA_PAIS) ) );
        dieta.setCiudad( cursor.getString(cursor.getColumnIndex(Constantes.DIETA_CIUDAD) ) );
        dieta.setDieta( cursor.getDouble(cursor.getColumnIndex(Constantes.DIETA_DIETA) ) );
        dieta.setProyect( cursor.getString(cursor.getColumnIndex(Constantes.DIETA_PRO) ) );
        dieta.setDepartment( cursor.getString(cursor.getColumnIndex(Constantes.DIETA_DEP) ) );
        return dieta;
    }

    private Dieta getResultOne(Cursor cursor) throws Exception{
        if (cursor.moveToNext()){
            Dieta dieta = new Dieta();
            getNextCursor(cursor, dieta);
            return dieta;
        } else {
            throw new Exception("No existe el registro");
        }
    }

    private List<Dieta> getResultList(Cursor cursor) {
        List<Dieta> dietas = new ArrayList<Dieta>();
        System.out.println(cursor.getCount());
        int contador = 0;
        if (cursor.moveToFirst()) {
            do {
                System.out.println(contador++);
                Dieta dieta = new Dieta();
                dieta = getNextCursor(cursor, dieta);
                dietas.add(dieta);
            } while (cursor.moveToNext());
        }
        return dietas;
    }
}
