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
import com.example.appcursoandroidv2.database.Constantes;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.regex.Pattern;

public class FiltroFragment extends Fragment {

    EditText etFechaDesde, etFechaHasta, etImporteDesde, etImporteHasta;
    TextInputLayout lyFechaDesde, lyFechaHasta;
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
        setEventListeners();
        return view;
    }

    private void getControlViews() {
        etFechaDesde = view.findViewById(R.id.et_fecha_desde);
        etFechaHasta = view.findViewById(R.id.et_fecha_hasta);
        etImporteDesde = view.findViewById(R.id.et_importe_desde);
        etImporteHasta = view.findViewById(R.id.et_importe_hasta);
        lyFechaDesde = view.findViewById(R.id.ly_fecha_desde);
        lyFechaHasta = view.findViewById(R.id.ly_fecha_hasta);
    }

    private void setEventListeners() {
        etFechaDesde.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validarDesdeFecha();
                }
            }
        });
        etFechaDesde.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            /**
             * Solo para que cuando corrige una fecha errónea se quite el mensaje de error
             * La validacion la hace el método validar()
             **/
            @Override
            public void afterTextChanged(Editable s) {
                Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
                if(pattern.matcher(etFechaDesde.getText().toString()).matches()) {
                    lyFechaDesde.setError(null);
                }
            }
        });
        etFechaHasta.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validarHastaFecha();
                }
            }
        });
        etFechaHasta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            /**
             * Solo para que cuando corrige una fecha errónea se quite el mensaje de error
             * La validacion la hace el método validar()
             **/
            @Override
            public void afterTextChanged(Editable s) {
                Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
                if(pattern.matcher(etFechaHasta.getText().toString()).matches()) {
                    lyFechaHasta.setError(null);
                }
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

    public boolean validarDesdeFecha() {
        Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
        if(!pattern.matcher(etFechaDesde.getText().toString()).matches() && !etFechaDesde.getText().toString().isEmpty()) {
            lyFechaDesde.setError("Fecha incorrecta");
            return false;
        } else {
            lyFechaDesde.setError(null);
            return true;
        }
    }

    public boolean validarHastaFecha() {
        Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
        if(!pattern.matcher(etFechaHasta.getText().toString()).matches() && !etFechaHasta.getText().toString().isEmpty()) {
            lyFechaHasta.setError("Fecha incorrecta");
            return false;
        } else {
            lyFechaHasta.setError(null);
            return true;
        }
    }
}