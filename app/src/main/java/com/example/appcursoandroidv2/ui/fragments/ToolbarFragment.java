package com.example.appcursoandroidv2.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.ui.activtar.ActivTarActivity;
import com.example.appcursoandroidv2.ui.adiciongasto.AdicionGastoActivity;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.example.appcursoandroidv2.ui.listagastos.ListaGastosActivity;
import com.google.android.material.appbar.MaterialToolbar;


public class ToolbarFragment extends Fragment {

    Context context;
    MaterialToolbar topAppBar;

    public ToolbarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);

        context = view.getContext();
        topAppBar = view.findViewById(R.id.topAppBar);

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.inicio:
                        intent = new Intent(context, InicioActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.gastos:
                        intent = new Intent(context, AdicionGastoActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.dietas:
                        Toast.makeText(context, "Has seleccionado dietas", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.list_gastos:
                        intent = new Intent(context, ListaGastosActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.activ_tarjetas:
                        intent = new Intent(context, ActivTarActivity.class);
                        startActivity(intent);
                }
                return false;
            }
        });

        return view;


    }
}
