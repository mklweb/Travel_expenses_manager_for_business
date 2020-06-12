package com.example.appcursoandroidv2.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.appcursoandroidv2.R;
import java.util.HashMap;

public class FiltroFragment extends Fragment {

    EditText etFechaDesde, etFechaHasta, etImporteDesde, etImporteHasta;
    View view;

    public FiltroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filtro, container, false);
        getControlViews();
        return view;
    }

    private void getControlViews() {
        etFechaDesde = view.findViewById(R.id.et_fecha_desde);
        etFechaHasta = view.findViewById(R.id.et_fecha_hasta);
        etImporteDesde = view.findViewById(R.id.et_importe_desde);
        etImporteHasta = view.findViewById(R.id.et_importe_hasta);
    }

    private void validarCampos() {
        etFechaDesde.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etFechaHasta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etImporteDesde.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etImporteHasta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public HashMap<String, String> getDatosFromView() {
        HashMap<String, String> params = new HashMap<String, String>();
        //Campo fecha desde
        String strAux;
        strAux = etFechaDesde.getText().toString();
        if(!strAux.isEmpty()) {
            params.put("desde fecha", strAux);
        }
        //Campo fecha hasta
        strAux = etFechaHasta.getText().toString();
        if(!strAux.isEmpty()) {
            params.put("hasta fecha", strAux);
        }
        //Campo importeDesde
        strAux = etImporteDesde.getText().toString();
        if(!strAux.isEmpty()) {
            params.put("desde importe", strAux);
        }
        //Campo importeHasta
        strAux = etImporteHasta.getText().toString();
        if(!strAux.isEmpty()) {
            params.put("hasta importe", strAux);
        }
        return params;
    }
}