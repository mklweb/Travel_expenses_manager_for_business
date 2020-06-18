package com.example.appcursoandroidv2.dao;

import android.content.ContentValues;

import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.entidades.Gasto;

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
}
