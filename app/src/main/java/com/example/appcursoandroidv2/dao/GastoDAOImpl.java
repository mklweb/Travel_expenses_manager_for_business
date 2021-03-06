package com.example.appcursoandroidv2.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.utils.DateParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GastoDAOImpl implements GastoDAO {

    SQLiteDatabase db;

    /** Para SELECT sin asterisco
     * Si se añade o elimina una columna a la tabla
     * ponerla aquí, en getContentValues() y en getNextCursor*/
    private static final String[] COLUMNS = {
            Constantes.GASTO_FECHA,
            Constantes.GASTO_TRANSPORTE,
            Constantes.GASTO_KILOMETRAJE,
            Constantes.GASTO_PRECIO_KM,
            Constantes.GASTO_PEAJE,
            Constantes.GASTO_PARKING,
            Constantes.GASTO_RESTAURANTE,
            Constantes.GASTO_OTROS,
            Constantes.GASTO_DEP,
            Constantes.GASTO_PRO};

    public GastoDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public long add(Gasto gasto) throws Exception {
        return db.insert(Constantes.TABLA_GASTO, null, getContentValues(gasto));
    }

    @Override
    public int remove(String id) throws Exception {
        String[] args = new String[]{id};
        return db.delete(Constantes.TABLA_GASTO, Constantes.GASTO_ID + "=?", args);
    }

    @Override
    public int modify(Gasto gasto) throws Exception {
        String[] args = new String[]{String.valueOf(gasto.getId())};
        return db.update(Constantes.TABLA_GASTO, getContentValues(gasto), Constantes.GASTO_ID + "=?", args);
    }

    @Override
    public Gasto findById(long id) throws Exception {
        String qry = "SELECT * FROM " + Constantes.TABLA_GASTO + " WHERE " + Constantes.GASTO_ID + "=" + id;
        Cursor cursor = db.rawQuery(qry, null);
        return getResultOne(cursor);
    }

    @Override
    public List<Gasto> findAll() throws Exception {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("SELECT ").append(Constantes.GASTO_ID);
        for (int i = 0, n = COLUMNS.length; i < n; i++) {
            strBuilder.append(", ").append(COLUMNS[i]);
        }
        strBuilder.append(" FROM ").append(Constantes.TABLA_GASTO);
        strBuilder.append(" ORDER BY ").append(Constantes.GASTO_FECHA).append(" DESC");
        String sql = strBuilder.toString();
        Cursor cursor = db.rawQuery(sql, null);
        List<Gasto> gastos = getResultList(cursor);
        return gastos;
    }

    public List<Gasto> filtrarGastos(HashMap<String, String> params ) {

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
        strBuilder.append("SELECT ").append(Constantes.GASTO_ID);
        for (int i = 0, n = COLUMNS.length; i < n; i++) {
            strBuilder.append(", ").append(COLUMNS[i]);
        }
        strBuilder.append(" FROM ").append(Constantes.TABLA_GASTO);
        strBuilder.append(" WHERE 1=1");
        if(desdeFecha > 0) {
            strBuilder.append(" AND ").append(Constantes.GASTO_FECHA).append(" >= ").append(desdeFecha);
        }
        if(hastaFecha > 0) {
            strBuilder.append(" AND ").append(Constantes.GASTO_FECHA).append(" <= ").append(hastaFecha);
        }
        if(desdeImporte > 0) {
            strBuilder.append(" AND ")
                    .append(Constantes.GASTO_TRANSPORTE)
                    .append("+")
                    .append(Constantes.GASTO_KILOMETRAJE)
                    .append("*")
                    .append(Constantes.GASTO_PRECIO_KM)
                    .append("+").append(Constantes.GASTO_PEAJE )
                    .append("+")
                    .append( Constantes.GASTO_PARKING)
                    .append("+")
                    .append( Constantes.GASTO_RESTAURANTE)
                    .append("+")
                    .append( Constantes.GASTO_OTROS)
                    .append(" >= ")
                    .append(desdeImporte);
        }
        if(hastaImporte > 0) {
            strBuilder.append(" AND ")
                    .append(Constantes.GASTO_TRANSPORTE)
                    .append("+")
                    .append(Constantes.GASTO_KILOMETRAJE)
                    .append("*")
                    .append(Constantes.GASTO_PRECIO_KM)
                    .append("+").append(Constantes.GASTO_PEAJE )
                    .append("+")
                    .append( Constantes.GASTO_PARKING)
                    .append("+")
                    .append( Constantes.GASTO_RESTAURANTE)
                    .append("+")
                    .append( Constantes.GASTO_OTROS)
                    .append(" <= ")
                    .append(hastaImporte);
        }
        strBuilder.append(" ORDER BY ").append(Constantes.GASTO_FECHA).append(" ASC");
        String sql = strBuilder.toString();
        Cursor cursor = db.rawQuery(sql, null);
        List<Gasto> gastos = getResultList(cursor);
        return gastos;
    }

    public void removeAll() throws Exception {
        String sql = "DELETE FROM " + Constantes.TABLA_GASTO;
        db.execSQL(sql);
    }

    private ContentValues getContentValues(Gasto gasto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.GASTO_FECHA, gasto.getFecha());
        contentValues.put(Constantes.GASTO_TRANSPORTE, gasto.getTransporte());
        contentValues.put(Constantes.GASTO_KILOMETRAJE, gasto.getKilometraje());
        contentValues.put(Constantes.GASTO_PRECIO_KM, gasto.getPrecioKm());
        contentValues.put(Constantes.GASTO_PEAJE, gasto.getPeaje());
        contentValues.put(Constantes.GASTO_PARKING, gasto.getParking());
        contentValues.put(Constantes.GASTO_RESTAURANTE, gasto.getRestaurante());
        contentValues.put(Constantes.GASTO_OTROS, gasto.getOtros());
        contentValues.put(Constantes.GASTO_DEP, gasto.getDep());
        contentValues.put(Constantes.GASTO_PRO, gasto.getPro());
        return contentValues;
    }

    private Gasto getNextCursor(Cursor cursor, Gasto gasto) {
        gasto.setId( cursor.getLong(cursor.getColumnIndex(Constantes.GASTO_ID) ) );
        gasto.setFecha( cursor.getLong(cursor.getColumnIndex(Constantes.GASTO_FECHA) ) );
        gasto.setTransporte( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_TRANSPORTE) ) );
        gasto.setKilometraje( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_KILOMETRAJE) ) );
        gasto.setPrecioKm( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_PRECIO_KM) ) );
        gasto.setPeaje( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_PEAJE) ) );
        gasto.setParking( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_PARKING) ) );
        gasto.setRestaurante( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_RESTAURANTE) ) );
        gasto.setOtros( cursor.getDouble(cursor.getColumnIndex(Constantes.GASTO_OTROS) ) );
        gasto.setDep( cursor.getString(cursor.getColumnIndex(Constantes.GASTO_DEP) ) );
        gasto.setPro( cursor.getString(cursor.getColumnIndex(Constantes.GASTO_PRO) ) );
        return gasto;
    }

    private Gasto getResultOne(Cursor cursor) throws Exception{
        if (cursor.moveToNext()){
            Gasto gasto = new Gasto();
            getNextCursor(cursor, gasto);
            return gasto;
        } else {
            throw new Exception("No existe el registro");
        }
    }

    private List<Gasto> getResultList(Cursor cursor) {
        List<Gasto> gastos = new ArrayList<Gasto>();
        System.out.println(cursor.getCount());
        int contador = 0;
        if (cursor.moveToFirst()) {
            do {
                System.out.println(contador++);
                Gasto gasto = new Gasto();
                gasto = getNextCursor(cursor, gasto);
                gastos.add(gasto);
            } while (cursor.moveToNext());
        }
        return gastos;
    }
}
