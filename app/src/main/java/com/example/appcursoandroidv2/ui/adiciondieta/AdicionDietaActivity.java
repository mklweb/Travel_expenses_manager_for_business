package com.example.appcursoandroidv2.ui.adiciondieta;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.PrecioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.ui.activartar.ActivarTarActivity;
import com.example.appcursoandroidv2.ui.fragments.FormularioDietaFragment;
import com.example.appcursoandroidv2.ui.fragments.FormularioGastoFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class AdicionDietaActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText etDieta, etTotal;
    FormularioDietaFragment fragment;
    TextInputLayout lyTotal;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicion_dieta);

        getControlViews();
        setEventListeners();
        etTotal.setVisibility(View.GONE);
        lyTotal.setVisibility(View.GONE);
        fragment = (FormularioDietaFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_dieta_fragment);
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle("Nueva dieta");

    }

    private void getControlViews() {
        btnEnviar = findViewById(R.id.bt_enviar);
        etDieta = findViewById(R.id.et_dieta);
        etTotal = findViewById(R.id.et_total_dieta);
        lyTotal = findViewById(R.id.ly_total_dieta);
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