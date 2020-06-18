package com.example.appcursoandroidv2.ui.listagastos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.ui.detallegasto.DetalleGastoActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListaGastosActivity extends AppCompatActivity {

    RecyclerView recyclerGastos;
    RecyclerView.LayoutManager layoutManager;
    AdapterGastos adapterGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_gastos);

        recyclerGastos = findViewById(R.id.recycler_gastos);
        layoutManager = new LinearLayoutManager(this);
        recyclerGastos.setLayoutManager(layoutManager);

        //Para recoger parámetros por intent
        HashMap<String, String> params = (HashMap<String, String>) getIntent().getSerializableExtra("params");

        //ViewModel
        ListaGastosModel listaGastosModel = new ViewModelProvider(this).get(ListaGastosModel.class);
        listaGastosModel.getGastos(params).observe(this, new Observer<List<Gasto>>() {
            @Override
            public void onChanged(final List<Gasto> gastos) {
                // Instanciamos el adaptador
                adapterGastos = new AdapterGastos(gastos);
                //Asignamos el listener al adaptador para cuando hace click en un item
                adapterGastos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), DetalleGastoActivity.class);
                        // Cogemos el gasto correspondiente al item clickado y se lo pasamos en el intent
                        // a la activity de destino
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("gasto", gastos.get(recyclerGastos.getChildAdapterPosition(v)));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                //Pasamos el adaptador al recycler
                recyclerGastos.setAdapter(adapterGastos);
            }
        });
    }
}
