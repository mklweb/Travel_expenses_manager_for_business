package com.example.appcursoandroidv2.ui.listagastos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.ui.ediciongasto.EdicionGastoActivity;
import com.example.appcursoandroidv2.ui.fragments.FiltroFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class FiltroGastosActivity extends AppCompatActivity {

    Button btnFitroGastosEnviar;
    FiltroFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_gastos);

        btnFitroGastosEnviar = findViewById(R.id.bt_filtro_gastos_enviar);

        fragment = (FiltroFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_filtro);

        btnFitroGastosEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment.validarDesdeFecha() && fragment.validarHastaFecha()) {
                    HashMap<String, String> params = fragment.getDatosFromView();
                    Intent intent = new Intent(FiltroGastosActivity.this, ListaGastosActivity.class);
                    intent.putExtra("params", params);
                    startActivity(intent);
                }
            }
        });
    }
}