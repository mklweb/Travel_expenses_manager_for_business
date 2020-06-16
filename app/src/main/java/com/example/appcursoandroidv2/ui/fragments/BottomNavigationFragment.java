package com.example.appcursoandroidv2.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.ui.adiciongasto.AdicionGastoActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomNavigationFragment extends Fragment {
    Intent intent;
    Context context;
    BottomNavigationView bottomNavigation;
    final static int PERMISOS_LLAMADA = 101;

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
                        Intent call = new Intent(Intent.ACTION_CALL);
                        call.setData(Uri.parse("tel:" + "1515"));
                        if (ActivityCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                requestPermissions(new String[]{
                                    Manifest.permission.CALL_PHONE},PERMISOS_LLAMADA);
                        }else{
                            startActivity(call);
                        }
                        break;
                    case R.id.btm_gasto:
                        item.setChecked(true);
                        intent = new Intent(context, AdicionGastoActivity.class);
                        startActivity(intent);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PERMISOS_LLAMADA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:" + "1515"));
                    startActivity(call);
                } else {
                    Toast.makeText(context, "Sin los permisos de localización no podrás activar las tarjetas", Toast.LENGTH_SHORT).show();
                }
                break;

            // Aquí más casos dependiendo de los permisos
            // case OTRO_CODIGO_DE_PERMISOS...
        }
    }
}
