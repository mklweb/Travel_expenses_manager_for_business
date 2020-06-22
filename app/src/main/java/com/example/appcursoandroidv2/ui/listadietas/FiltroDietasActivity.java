package com.example.appcursoandroidv2.ui.listadietas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.ui.fragments.FiltroFragment;

import java.util.HashMap;

public class FiltroDietasActivity extends AppCompatActivity {

    Button btnFitroDietasEnviar;
    FiltroFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtro_dietas);

        btnFitroDietasEnviar = findViewById(R.id.bt_filtro_dietas_enviar);

        fragment = (FiltroFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_filtro);

        btnFitroDietasEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment.validarDesdeFecha() && fragment.validarHastaFecha()) {
                    HashMap<String, String> params = fragment.getDatosFromView();
                    Intent intent = new Intent(FiltroDietasActivity.this, ListaDietasActivity.class);
                    intent.putExtra("params", params);
                    startActivity(intent);
                }
            }
        });
    }
}