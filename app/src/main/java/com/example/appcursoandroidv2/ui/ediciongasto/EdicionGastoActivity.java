package com.example.appcursoandroidv2.ui.ediciongasto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.ui.fragments.FormularioGastoFragment;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.example.appcursoandroidv2.ui.listagastos.ListaGastosActivity;
import com.google.android.material.appbar.MaterialToolbar;


public class EdicionGastoActivity extends AppCompatActivity {

    //private static final int SCREEN_ORIENTATION_PORTRAIT = 1;
    Button btnEnviar;
    FormularioGastoFragment fragment;
    MaterialToolbar toolbar;
    //@SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(EdicionGastoActivity.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_edicion_gasto);

        getControlViews();

        setEventListeners();

        fragment = (FormularioGastoFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_gasto_fragment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gasto gasto = (Gasto) bundle.getSerializable("gasto");
            fragment.setDatosToView(gasto);
        }
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle(R.string.titulo_actualiza_gastos);
    }

    private void getControlViews() {
        btnEnviar = findViewById(R.id.bt_update_gasto);
    }

    private void setEventListeners() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment.actualizar()) {
                    Intent intent = new Intent(EdicionGastoActivity.this, ListaGastosActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
}
