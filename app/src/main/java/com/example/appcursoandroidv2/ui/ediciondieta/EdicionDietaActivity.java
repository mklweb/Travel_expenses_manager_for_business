package com.example.appcursoandroidv2.ui.ediciondieta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.ui.ediciongasto.EdicionGastoActivity;
import com.example.appcursoandroidv2.ui.fragments.FormularioDietaFragment;
import com.example.appcursoandroidv2.ui.fragments.FormularioGastoFragment;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.example.appcursoandroidv2.ui.listadietas.ListaDietasActivity;
import com.example.appcursoandroidv2.ui.listagastos.ListaGastosActivity;

public class EdicionDietaActivity extends AppCompatActivity {

    Button btnEnviar;
    FormularioDietaFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_dieta);

        getControlViews();
        setEventListeners();

        fragment = (FormularioDietaFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_dieta_fragment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Dieta dieta = (Dieta) bundle.getSerializable("dieta");
            fragment.setDatosToView(dieta);
        }
    }

    private void getControlViews() {
        btnEnviar = findViewById(R.id.bt_enviar);
    }

    private void setEventListeners() {
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragment.actualizar()) {
                    Intent intent = new Intent(EdicionDietaActivity.this, ListaDietasActivity.class);
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