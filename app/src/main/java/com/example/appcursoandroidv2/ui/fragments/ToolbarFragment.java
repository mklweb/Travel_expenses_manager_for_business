package com.example.appcursoandroidv2.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.ui.activartar.ActivarTarActivity;
import com.example.appcursoandroidv2.ui.adiciongasto.AdicionGastoActivity;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.example.appcursoandroidv2.ui.listagastos.FiltroGastosActivity;
import com.example.appcursoandroidv2.ui.listagastos.ListaGastosActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


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
        //userDaoImpl = new UserDaoImpl();
        //user = userDaoImpl.findByName(loginModel.getNombre());
        //String src = user.getSrc();
        String src = "https://mikelweb.ml/img/foto_freddieXL.png";
        View view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        ImageView ivFoto= view.findViewById(R.id.fotoItem);
        LoadImage loadimage = new LoadImage(ivFoto);
        loadimage.execute(src);
        context = view.getContext();
        topAppBar = view.findViewById(R.id.topAppBar);
        //topAppBar.setNavigationIcon(R.drawable.ic_launcher_foreground);
        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.inicio:
                        intent = new Intent(context, InicioActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.list_gastos:
                        intent = new Intent(context, ListaGastosActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.find_gastos:
                        intent = new Intent(context, FiltroGastosActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.list_dietas:
                        Toast.makeText(context, "Has seleccionado LISTA DIETA", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.find_dietas:
                        Toast.makeText(context, "Has seleccionado BUSQUEDA DIETA", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.activ_tarjetas:
                        intent = new Intent(context, ActivarTarActivity.class);
                        startActivity(intent);
                }
                return false;
            }
        });
        return view;
    }
    private class LoadImage  extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView2;
        public LoadImage(ImageView imageView2) {
            this.imageView2= imageView2;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String urLink= strings[0];
            Bitmap bitmap= null;
            try {
                InputStream inputStream = new URL(urLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView2.setImageBitmap(bitmap);
        }
    }
}
