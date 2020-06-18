package com.example.appcursoandroidv2.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.entidades.Gasto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DietaDAOImpl implements DietaDAO {


    @Override
    public long add(Dieta dieta) throws Exception {
        return 0;
    }

    @Override
    public int remove(String id) throws Exception {
        return 0;
    }

    @Override
    public int modify(Dieta dieta) throws Exception {
        return 0;
    }

    @Override
    public Dieta findById(long id) throws Exception {
        return null;
    }

    @Override
    public List<Dieta> findAll() throws Exception {
        return null;
    }

    public List<Dieta> filtrarDietas(HashMap<String, String> params ) {
        return  null;
    }

    private ContentValues getContentValues(Dieta dieta) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.DIETA_FECHA_INI, dieta.getFechaIni());
        contentValues.put(Constantes.DIETA_FECHA_FIN, dieta.getFechaFin());
        contentValues.put(Constantes.DIETA_PAIS, dieta.getPais());
        contentValues.put(Constantes.DIETA_CIUDAD, dieta.getCiudad());
        contentValues.put(Constantes.DIETA_DIETA, dieta.getDieta());
        return contentValues;
    }

    private Dieta getNextCursor(Cursor cursor, Dieta dieta) {
        dieta.setId( cursor.getLong(cursor.getColumnIndex(Constantes.DIETA_ID) ) );
        dieta.setFechaIni( cursor.getLong(cursor.getColumnIndex(Constantes.DIETA_FECHA_INI) ) );
        dieta.setFechaFin( cursor.getLong(cursor.getColumnIndex(Constantes.DIETA_FECHA_FIN) ) );
        dieta.setPais( cursor.getString(cursor.getColumnIndex(Constantes.DIETA_PAIS) ) );
        dieta.setCiudad( cursor.getString(cursor.getColumnIndex(Constantes.DIETA_CIUDAD) ) );
        dieta.setDieta( cursor.getDouble(cursor.getColumnIndex(Constantes.DIETA_DIETA) ) );
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
