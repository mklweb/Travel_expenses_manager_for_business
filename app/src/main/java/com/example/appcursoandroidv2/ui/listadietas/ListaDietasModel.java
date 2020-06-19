package com.example.appcursoandroidv2.ui.listadietas;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appcursoandroidv2.dao.DietaDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Dieta;

import java.util.HashMap;
import java.util.List;

public class ListaDietasModel extends AndroidViewModel {

    private MutableLiveData<List<Dieta>> dietas;
    private Context context;

    public ListaDietasModel(@NonNull Application application) {
        super(application);
        this.context = getApplication().getApplicationContext();
    }

    public LiveData<List<Dieta>> getDietas(HashMap<String, String> params) {
        if(dietas == null) {
            dietas = new MutableLiveData<List<Dieta>>();
            if(params == null) {
                loadDietas();
            } else {
                filtrarDietas(params);
            }
        }
        return dietas;
    }

    private void loadDietas() {
        SQLiteDatabase db = Conexion.getInstance(context);
        DietaDAOImpl dietaDAO = new DietaDAOImpl(db);
        try {
            List<Dieta> listDietas = dietaDAO.findAll();
            dietas.setValue(listDietas);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtrarDietas(HashMap<String, String> params) {
        SQLiteDatabase db = Conexion.getInstance(context);
        DietaDAOImpl dietaDAO = new DietaDAOImpl(db);
        try {
            List<Dieta> listDietas = dietaDAO.filtrarDietas(params);
            dietas.setValue(listDietas);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
