package com.example.appcursoandroidv2.ui.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.GastoDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.utils.DateParser;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioGastoFragment extends Fragment {

    Gasto gasto = null;

    EditText etDateGasto;
    EditText etTransport;
    EditText etKilometers;
    EditText etKmPrice;
    EditText etToll;
    EditText etParking;
    EditText etFood;
    EditText etOther;
    EditText etProjectGasto;
    EditText etDeparmentGasto;
    EditText etTotalGasto;

    View view;
    FragmentActivity fragmentActivity;

    public FormularioGastoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_formulario_gasto, container, false);
        getControlViews();
        return view;
    }

    private void getControlViews() {

        etDateGasto = view.findViewById(R.id.et_date_gasto);
        etTransport = view.findViewById(R.id.et_transport);
        etKilometers = view.findViewById(R.id.et_kilometers);
        etKmPrice = view.findViewById(R.id.et_km_price);
        etToll = view.findViewById(R.id.et_toll);
        etParking = view.findViewById(R.id.et_parking);
        etFood = view.findViewById(R.id.et_food);
        etOther = view.findViewById(R.id.et_other);
        etProjectGasto = view.findViewById(R.id.et_project_gasto);
        etDeparmentGasto = view.findViewById(R.id.et_department_gasto);
        etTotalGasto = view.findViewById(R.id.et_toll);
    }

    public void setDatosToView(Gasto gasto) {
        // Cuando viene de modificar recibe un gasto
        // Cuando está creando uno nuevo no recibe nada.
        this.gasto = gasto;
        DateParser dp = new DateParser(new Date(gasto.getFecha()));
        etDateGasto.setText(dp.getDateInTextFormat());
        etTransport.setText(String.valueOf(gasto.getTransporte()));
        etKilometers.setText(String.valueOf(gasto.getKilometraje()));
        etKmPrice.setText(String.valueOf(gasto.getPrecioKm()));
        etToll.setText(String.valueOf(gasto.getPeaje()));
        etParking.setText(String.valueOf(gasto.getParking()));
        etFood.setText(String.valueOf(gasto.getRestaurante()));
        etOther.setText(String.valueOf(gasto.getOtros()));
        etProjectGasto.setText(String.valueOf(gasto.getPro()));
        etDeparmentGasto.setText(String.valueOf(gasto.getDep()));
    }

    private void getDatosFromView() {
        // Cuando se crea un nuevo gasto
        if(gasto == null) {
            gasto = new Gasto();
        }
        //Campo fecha
        String strAux;
        DateParser dp = new DateParser();
        strAux = etDateGasto.getText().toString();
        long msFecha = dp.parse(strAux);
        gasto.setFecha(msFecha);
        //Campo transporte
        strAux = etTransport.getText().toString();
        double transporte;
        if(!strAux.isEmpty()){
            transporte = Double.valueOf(strAux);
        } else {
            transporte = 0;
        }
        gasto.setTransporte(transporte);
        //Campo kilometros
        strAux = etKilometers.getText().toString();
        double kilometraje;
        if(!strAux.isEmpty()){
            kilometraje = Double.valueOf(strAux);
        } else {
            kilometraje = 0;
        }
        gasto.setKilometraje(kilometraje);
        //Campo precio kiómetro
        strAux = etKmPrice.getText().toString();
        double precioKm;
        if(!strAux.isEmpty()){
            precioKm = Double.valueOf(strAux);
        } else {
            precioKm = 0;
        }
        gasto.setPrecioKm(precioKm);
        //Campo peaje
        strAux = etToll.getText().toString();
        double peaje;
        if(!strAux.isEmpty()) {
            peaje = Double.valueOf(strAux);
        } else {
            peaje = 0;
        }
        gasto.setPeaje(peaje);
        //Campo parking
        strAux = etParking.getText().toString();
        double parking;
        if(!strAux.isEmpty()) {
            parking = Double.valueOf(strAux);
        } else {
            parking = 0;
        }
        gasto.setParking(parking);
        //Comida
        strAux = etFood.getText().toString();
        double restaurante;
        if(!strAux.isEmpty()) {
            restaurante = Double.valueOf(strAux);
        } else {
            restaurante = 0;
        }
        gasto.setRestaurante(restaurante);
        //Otros conceptos
        strAux = etOther.getText().toString();
        double otros;
        if(!strAux.isEmpty()) {
            otros = Double.valueOf(strAux);
        } else {
            otros = 0;
        }
        gasto.setOtros(otros);
        //Departamento
        strAux = etDeparmentGasto.getText().toString();
        gasto.setDep(strAux);
        //Proyecto
        strAux = etProjectGasto.getText().toString();
        gasto.setPro(strAux);
        //Total gasto
        strAux = etTotalGasto.getText().toString();
        double totalGasto;
        if(!strAux.isEmpty()) {
            totalGasto = Double.valueOf(strAux);
        } else {
            totalGasto = 0;
        }
    }

    public void registrar() {
        SQLiteDatabase db = Conexion.getInstance(getActivity());
        GastoDAOImpl gastoDAO = new GastoDAOImpl(db);
        getDatosFromView();
        try {
            long n = gastoDAO.add(gasto);
            db.close();
            resetFormulario();
            Toast.makeText(getActivity().getApplicationContext(), "Se ha creado un gasto nuevo con id " + n, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {
        SQLiteDatabase db = Conexion.getInstance(getActivity());
        GastoDAOImpl gastoDAO = new GastoDAOImpl(db);
        getDatosFromView();
        try {
            gastoDAO.modify(gasto);
            db.close();
            resetFormulario();
            Toast.makeText(getActivity().getApplicationContext(), "Se han guardado los cambios", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrar() {
        SQLiteDatabase db = Conexion.getInstance(getActivity());
        GastoDAOImpl gastoDAO = new GastoDAOImpl(db);
        try {
            gastoDAO.remove(String.valueOf(gasto.getId()));
            db.close();
            Toast.makeText(getActivity().getApplicationContext(), "El gasto ha sido borrado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetFormulario() {
        etDateGasto.setText("");
        etTransport.setText("");
        etKilometers.setText("");
        etKmPrice.setText("");
        etToll.setText("");
        etParking.setText("");
        etFood.setText("");
        etOther.setText("");
        etProjectGasto.setText("");
        etDeparmentGasto.setText("");
    }

    public Gasto getGasto() {
        return gasto;
    }
}
