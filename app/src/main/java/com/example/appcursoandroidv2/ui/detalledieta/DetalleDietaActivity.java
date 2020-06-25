package com.example.appcursoandroidv2.ui.detalledieta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.ui.ediciondieta.EdicionDietaActivity;
import com.example.appcursoandroidv2.ui.fragments.FormularioDietaFragment;
import com.example.appcursoandroidv2.ui.listadietas.ListaDietasActivity;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.hbb20.CountryCodePicker;

public class DetalleDietaActivity extends AppCompatActivity {

    Button btnBorrar;
    Button btnActualizar;
    FormularioDietaFragment fragment;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_dieta);

        getControlViews();

        setEventListeners();

        fragment = (FormularioDietaFragment) getSupportFragmentManager().findFragmentById(R.id.formulario_dieta_fragment);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Dieta dieta = (Dieta) bundle.getSerializable("dieta");
            fragment.setDatosToView(dieta);
        }
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle("Detalle de dieta");
    }

    private void getControlViews(){
        btnBorrar = findViewById(R.id.bt_delete_dieta);
        btnActualizar = findViewById(R.id.bt_update_dieta);
        findViewById(R.id.et_start_date_dieta).setFocusable(false);
        findViewById(R.id.et_end_date_dieta).setFocusable(false);

        CountryCodePicker ccp = findViewById(R.id.ccp);
        ccp.setCcpClickable(false);
        ccp.showArrow(false);
        findViewById(R.id.et_city).setFocusable(false);
        findViewById(R.id.et_project_dieta).setFocusable(false);
        findViewById(R.id.et_department_dieta).setFocusable(false);
        findViewById(R.id.et_dieta).setFocusable(false);
        findViewById(R.id.bt_enviar).setVisibility(View.GONE);
    }

    private void setEventListeners() {
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(DetalleDietaActivity.this);
                dialogBuilder.setTitle("Borrar dieta")
                        .setMessage("¿Seguro que quiere borrar la dieta?")
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Acción cancelada", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragment.borrar();
                                Intent intent = new Intent(DetalleDietaActivity.this, ListaDietasActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleDietaActivity.this, EdicionDietaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dieta", fragment.getDieta());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
