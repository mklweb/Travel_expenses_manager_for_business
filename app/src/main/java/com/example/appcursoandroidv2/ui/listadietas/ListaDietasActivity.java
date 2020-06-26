package com.example.appcursoandroidv2.ui.listadietas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Dieta;
import com.example.appcursoandroidv2.ui.detalledieta.DetalleDietaActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.HashMap;
import java.util.List;

public class ListaDietasActivity extends AppCompatActivity {

    RecyclerView recyclerDietas;
    RecyclerView.LayoutManager layoutManager;
    AdapterDietas adapterDietas;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dietas);
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle(R.string.listado_dietas);
        recyclerDietas = findViewById(R.id.recycler_dieta);
        layoutManager = new LinearLayoutManager(this);
        recyclerDietas.setLayoutManager(layoutManager);

        //Para recoger parámetros por intent
        HashMap<String, String> params = (HashMap<String, String>) getIntent().getSerializableExtra("params");

        //ViewModel
        ListaDietasModel listaDietasModel = new ViewModelProvider(this).get(ListaDietasModel.class);
        listaDietasModel.getDietas(params).observe(this, new Observer<List<Dieta>>() {
            @Override
            public void onChanged(final List<Dieta> dietas) {
                // Instanciamos el adaptador
                adapterDietas = new AdapterDietas(dietas);
                //Asignamos el listener al adaptador para cuando hace click en un item
                adapterDietas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), DetalleDietaActivity.class);
                        // Cogemos la dieta correspondiente al item clickado y se lo pasamos en el intent
                        // a la activity de destino
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("dieta", dietas.get(recyclerDietas.getChildAdapterPosition(v)));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                //Pasamos el adaptador al recycler
                recyclerDietas.setAdapter(adapterDietas);
            }
        });
    }
}
