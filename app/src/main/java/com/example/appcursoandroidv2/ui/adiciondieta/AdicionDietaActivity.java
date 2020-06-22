package com.example.appcursoandroidv2.ui.adiciondieta;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.PrecioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.ui.activartar.ActivarTarActivity;
import com.example.appcursoandroidv2.ui.fragments.FormularioDietaFragment;
import com.example.appcursoandroidv2.ui.fragments.FormularioGastoFragment;

public class AdicionDietaActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText etDieta;
    FormularioDietaFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicion_dieta);

        getControlViews();
        setEventListeners();

        fragment = (FormularioDietaFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_dieta_fragment);
    }

    private void getControlViews() {
        btnEnviar = findViewById(R.id.bt_create_dieta);
        etDieta = findViewById(R.id.et_dieta);
    }

    private void setEventListeners() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPrecioDieta();
                fragment.registrar();
            }
        });
    }

    private void getPrecioDieta() {

    }
}