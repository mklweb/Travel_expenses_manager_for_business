package com.example.appcursoandroidv2.ui.listagastos;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appcursoandroidv2.dao.GastoDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Gasto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListaGastosModel extends AndroidViewModel {

    private MutableLiveData<List<Gasto>> gastos;
    private Context context;

    public ListaGastosModel(@NonNull Application application) {
        super(application);
        this.context = getApplication().getApplicationContext();
    }

    public LiveData<List<Gasto>> getGastos(HashMap<String, String> params) {
        if(gastos == null) {
            gastos = new MutableLiveData<List<Gasto>>();
            if(params == null) {
                loadGastos();
            } else {
                filtrarGastos(params);
            }
        }
        return gastos;
    }

    private void loadGastos() {
        SQLiteDatabase db = Conexion.getInstance(context);
        GastoDAOImpl gastoDAO = new GastoDAOImpl(db);
        try {
            List<Gasto> listGastos = gastoDAO.findAll();
            gastos.setValue(listGastos);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtrarGastos(HashMap<String, String> params) {
        SQLiteDatabase db = Conexion.getInstance(context);
        GastoDAOImpl gastoDAO = new GastoDAOImpl(db);
        try {
            List<Gasto> listGastos = gastoDAO.filtrarGastos(params);
            gastos.setValue(listGastos);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
