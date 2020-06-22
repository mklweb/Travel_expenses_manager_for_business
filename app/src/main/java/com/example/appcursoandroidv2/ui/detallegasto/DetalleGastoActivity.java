package com.example.appcursoandroidv2.ui.detallegasto;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.ui.ediciongasto.EdicionGastoActivity;
import com.example.appcursoandroidv2.ui.fragments.FormularioGastoFragment;
import com.example.appcursoandroidv2.ui.listagastos.ListaGastosActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DetalleGastoActivity extends AppCompatActivity {

    Button btnBorrar, btnActualizar;
    FormularioGastoFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_gasto);

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
        btnBorrar = findViewById(R.id.bt_delete_gasto);
        btnActualizar = findViewById(R.id.bt_update_gasto);
        findViewById(R.id.et_date_gasto).setFocusable(false);
        findViewById(R.id.et_transport).setFocusable(false);
        findViewById(R.id.et_kilometers).setFocusable(false);
        findViewById(R.id.et_km_price).setFocusable(false);
        findViewById(R.id.et_toll).setFocusable(false);
        findViewById(R.id.et_parking).setFocusable(false);
        findViewById(R.id.et_food).setFocusable(false);
        findViewById(R.id.et_other).setFocusable(false);
        findViewById(R.id.et_project_gasto).setFocusable(false);
        findViewById(R.id.et_department_gasto).setFocusable(false);
        findViewById(R.id.et_total_gasto).setFocusable(false);
    }

    private void setEventListeners() {
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(DetalleGastoActivity.this);
                dialogBuilder.setTitle("Borrar gasto")
                        .setMessage("¿Seguro que quiere borrar el gasto?")
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
                                Intent intent = new Intent(DetalleGastoActivity.this, ListaGastosActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalleGastoActivity.this, EdicionGastoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("gasto", fragment.getGasto());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
