package com.example.appcursoandroidv2.ui.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.DietaDAOImpl;
import com.example.appcursoandroidv2.dao.GastoDAOImpl;
import com.example.appcursoandroidv2.dao.PrecioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.entidades.Precio;
import com.example.appcursoandroidv2.ui.adiciondieta.AdicionDietaActivity;
import com.example.appcursoandroidv2.utils.DateParser;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import java.util.Date;
import java.util.regex.Pattern;


public class FormularioDietaFragment extends Fragment {

    Dieta dieta = null;
    String strAux;
    EditText etStartDate, etEndDate, etCity, etProjectDieta, etDepartmentDieta, etDieta, etTotal;
    TextInputLayout lyStartDate, lyEndDate, lyCity, lyDieta;
    CountryCodePicker ccp;
    View view;
    String paisInicial;
    public FormularioDietaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_formulario_dieta, container, false);
        getControlViews();
        setEventListeners();
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            paisInicial = savedInstanceState.getString("paisInicio");
            ccp.setCountryForNameCode(paisInicial);
        } else {
            ccp.setCountryForNameCode("ES");
        }
        return view;
    }

    private void getControlViews() {
        etStartDate = view.findViewById(R.id.et_start_date_dieta);
        etEndDate = view.findViewById(R.id.et_end_date_dieta);
        ccp = (CountryCodePicker) view.findViewById(R.id.ccp);
        ccp.setCountryForNameCode("ES");
        etCity = view.findViewById(R.id.et_city);
        etProjectDieta = view.findViewById(R.id.et_project_dieta);
        etDepartmentDieta = view.findViewById(R.id.et_department_dieta);
        etDieta = view.findViewById(R.id.et_dieta);
        etTotal = view.findViewById(R.id.et_total_dieta);
        lyStartDate = view.findViewById(R.id.ly_start_date_dieta);
        lyEndDate = view.findViewById(R.id.ly_end_date_dieta);
        lyCity = view.findViewById(R.id.ly_city);
        lyDieta = view.findViewById(R.id.ly_city);
    }

    private void setEventListeners() {
        etStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(validaStartDate()) {
                        getDatosFromView();
                        etTotal.setText(dieta.getTotal());
                    }
                }
            }
        });
        etEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if(validaEndDate()) {
                        if(!validaDias()) {
                            lyEndDate.setErrorEnabled(true);
                            lyEndDate.setError("El mínimo de días para el cobro de dietas es 5");
                        } else {
                            lyEndDate.setError(null);
                            lyEndDate.setErrorEnabled(false);
                            getDatosFromView();
                            etTotal.setText(dieta.getTotal());
                        }
                    } else {
                        lyEndDate.setErrorEnabled(true);
                        lyEndDate.setError("Introduzca una fecha válida");
                    }
                }
            }
        });

        etStartDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /** Solo para que cuando corrige una fecha errónea se quite el mensaje de error
             *  La validacion la hace el método validaStartDate()
             *  */
            @Override
            public void afterTextChanged(Editable s) {
                Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
                if(pattern.matcher(etStartDate.getText().toString()).matches()) {
                    lyStartDate.setError(null);
                    lyStartDate.setErrorEnabled(false);
                }
            }
        });
        etEndDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /** Solo para que cuando corrige una fecha errónea se quite el mensaje de error
             *  La validacion la hace el método validaEndDate()
             *  */
            @Override
            public void afterTextChanged(Editable s) {
                Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
                if(pattern.matcher(etEndDate.getText().toString()).matches()) {
                    lyEndDate.setError(null);
                    lyStartDate.setErrorEnabled(false);
                }
            }
        });
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                String precioDieta;
                SQLiteDatabase db = Conexion.getInstance(getContext());
                PrecioDAOImpl precioDAO = new PrecioDAOImpl(db);
                try {
                    if (Constantes.UE.contains(ccp.getSelectedCountryNameCode())) {
                        //Toast.makeText(getContext() , "EUROPA" , Toast.LENGTH_SHORT).show();
                        precioDieta = precioDAO.getDietaEu();
                        etDieta.setText(precioDieta);
                        strAux = etDieta.getText().toString();
                        dieta.setDieta(Double.parseDouble(strAux));
                        if(validaStartDate() && validaEndDate()){
                            etTotal.setText(String.valueOf(dieta.getTotal()));
                        }
                    } else {
                        //Toast.makeText(getContext() , "No EUROPA" , Toast.LENGTH_SHORT).show();
                        precioDieta = precioDAO.getDietaRes();
                        etDieta.setText(precioDieta);
                        strAux = etDieta.getText().toString();
                        dieta.setDieta(Double.parseDouble(strAux));
                        if(validaStartDate() && validaEndDate()){
                            etTotal.setText(String.valueOf(dieta.getTotal()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /** Obtine los valores de los campos y los carga en el
     *  pojo dieta, si no tienen valores carga 0
     *  */
    public void getDatosFromView() {
        // Cuando se crea una nueva dieta
        if(dieta == null) {
            dieta = new Dieta();
        }
        //Campo etSartDate

        DateParser dp = new DateParser();
        strAux = etStartDate.getText().toString();
        long msFecha = dp.parse(strAux);
        dieta.setFechaIni(msFecha);
        //Campo etEndDate
        strAux = etEndDate.getText().toString();
        msFecha = dp.parse(strAux);
        dieta.setFechaFin(msFecha);
        //Campos texto
        String pais = ccp.getSelectedCountryNameCode();
        dieta.setPais(pais);
        dieta.setCiudad(etCity.getText().toString());
        dieta.setProyect(etProjectDieta.getText().toString());
        dieta.setDepartment(etDepartmentDieta.getText().toString());
        //Campo etDieta
        strAux = etDieta.getText().toString();
        if(!strAux.isEmpty()) {
            dieta.setDieta(Double.parseDouble(strAux));
        } else {
            dieta.setDieta(0);
        }
    }

    /** Cuando viene de modificar recibe una dieta
     *  y se llama a este método. Cuando está creando
     *  una dieta nueva no recibe nada
     *  */
    public void setDatosToView(Dieta dieta) {
        this.dieta = dieta;
        DateParser dp = new DateParser(new Date(dieta.getFechaIni()));
        etStartDate.setText(dp.getDateInTextFormat());
        dp.setDate(new Date(dieta.getFechaFin()));
        etEndDate.setText(dp.getDateInTextFormat());
        ccp.setCountryForNameCode(dieta.getPais());
        //etCountry.setText(dieta.getPais());
        etCity.setText(dieta.getCiudad());
        etDieta.setText(String.valueOf(dieta.getDieta()));
        etProjectDieta.setText(String.valueOf(dieta.getProyect()));
        etDepartmentDieta.setText(String.valueOf(dieta.getDepartment()));
        etTotal.setText(dieta.getTotal());
    }

    private boolean validaStartDate() {
        Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
        String date = etStartDate.getText().toString();
        if(date.isEmpty()) {
            lyStartDate.setErrorEnabled(true);
            lyStartDate.setError("Introduzca una fecha");
            return false;
        } else if(!pattern.matcher(date).matches()) {
            lyStartDate.setErrorEnabled(true);
            lyStartDate.setError("Fecha incorrecta");
            return false;
        } else {
            lyStartDate.setError(null);
            lyStartDate.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validaEndDate() {
        Pattern pattern = Pattern.compile(Constantes.DATE_VALIDATION);
        String date = etEndDate.getText().toString();
        if(date.isEmpty()) {
            return false;
        } else if(!pattern.matcher(date).matches()) {
            lyStartDate.setErrorEnabled(true);
            lyEndDate.setError("Fecha incorrecta");
            return false;
        } else {
            lyEndDate.setError(null);
            return true;
        }
    }


    private boolean validaCamposTexto() {
        boolean city = !etCity.getText().toString().isEmpty();
        boolean proDep = !etProjectDieta.getText().toString().isEmpty() || !etDepartmentDieta.getText().toString().isEmpty();

        if( city && proDep) {
            return true;
        } else {
            Toast.makeText(getContext() , "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean validaDias() {
        String strStartDate = etStartDate.getText().toString();
        String strEndDate = etEndDate.getText().toString();
        DateParser dp = new DateParser();
        long startDate = dp.parse(strStartDate);
        long endDate = dp.parse(strEndDate);
        long ms = endDate - startDate;
        if(ms > 0) {
            return  (int) ms/(24 * 3600 * 1000) + 1 > 4;
        } else {
            return  false;
        }
    }

    private void resetFormulario() {
        etStartDate.setText("");
        etEndDate.setText("");
        ccp.setCountryForNameCode("ES");
        etCity.setText("");
        etProjectDieta.setText("");
        etDepartmentDieta.setText("");
        etTotal.setText("");
        lyStartDate.setError(null);
        lyStartDate.setErrorEnabled(false);
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void registrar() {
        if(validaStartDate() && validaEndDate() && validaCamposTexto()) {
            getDatosFromView();
            SQLiteDatabase db = Conexion.getInstance(getActivity());
            DietaDAOImpl dietaDAO = new DietaDAOImpl(db);
            try {
                long n = dietaDAO.add(dieta);
                Toast.makeText(getContext() , "Se ha creado una dieta con el id: " + n, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                db.close();
                resetFormulario();
            }
        }
    }

    public boolean actualizar() {
        if(validaStartDate() && validaEndDate() && validaCamposTexto()) {
            getDatosFromView();
            SQLiteDatabase db = Conexion.getInstance(getActivity());
            DietaDAOImpl dietaDAO = new DietaDAOImpl(db);
            try {
                dietaDAO.modify(dieta);
                Toast.makeText(getContext() , "Se han guardado los cambios", Toast.LENGTH_SHORT).show();
                resetFormulario();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }finally {
                db.close();
            }
        } else {
            return false;
        }
    }

    public void borrar() {
        SQLiteDatabase db = Conexion.getInstance(getActivity());
        DietaDAOImpl dietaDAO = new DietaDAOImpl(db);
        try {
            int n = dietaDAO.remove(String.valueOf(dieta.getId()));
            Toast.makeText(getActivity().getApplicationContext(), n + " registro/s eliminado/s", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("paisInicio", ccp.getSelectedCountryNameCode());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}