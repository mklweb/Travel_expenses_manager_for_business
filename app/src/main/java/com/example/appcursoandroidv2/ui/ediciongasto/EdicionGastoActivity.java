package com.example.appcursoandroidv2.ui.ediciongasto;

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


public class EdicionGastoActivity extends AppCompatActivity {

    Button btnEnviar;
    FormularioGastoFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_gasto);

        getControlViews();

        setEventListeners();

        fragment = (FormularioGastoFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_gasto_fragment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gasto gasto = (Gasto) bundle.getSerializable("gasto");
            fragment.setDatosToView(gasto);
        }

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
