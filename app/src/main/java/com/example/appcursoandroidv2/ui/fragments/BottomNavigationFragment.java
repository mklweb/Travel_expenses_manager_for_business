package com.example.appcursoandroidv2.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appcursoandroidv2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavigationFragment extends Fragment {

    Context context;
    BottomNavigationView bottomNavigation;


    public BottomNavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        context = view.getContext();

        bottomNavigation = view.findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btm_llamar:
                        item.setChecked(true);
                        Toast.makeText(context, "Has seleccionado LLAMADA", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_gasto:
                        item.setChecked(true);
                        Toast.makeText(context, "Has seleccionado GASTO", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btm_dieta:
                        item.setChecked(true);
                        Toast.makeText(context, "Has seleccionado DIETA", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        return view;
    }
}
