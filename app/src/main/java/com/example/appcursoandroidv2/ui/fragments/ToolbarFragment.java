package com.example.appcursoandroidv2.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.UsuarioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.activartar.ActivarTarActivity;
import com.example.appcursoandroidv2.ui.adiciongasto.AdicionGastoActivity;
import com.example.appcursoandroidv2.ui.infoUsuario.InfoUsuarioActivity;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.example.appcursoandroidv2.ui.listadietas.FiltroDietasActivity;
import com.example.appcursoandroidv2.ui.listadietas.ListaDietasActivity;
import com.example.appcursoandroidv2.ui.listagastos.FiltroGastosActivity;
import com.example.appcursoandroidv2.ui.listagastos.ListaGastosActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class ToolbarFragment extends Fragment {
    View view;
    Context context;
    MaterialToolbar topAppBar;
    Usuario user;
    public ToolbarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_toolbar, container, false);
        context = view.getContext();
        topAppBar = view.findViewById(R.id.topAppBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(topAppBar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        setHasOptionsMenu(true);

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.inicio:
                        intent = new Intent(context, InicioActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.ic_showuser:
                        Intent sendIntent= new Intent(context, InfoUsuarioActivity.class);
                        startActivity(sendIntent);
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
                        intent = new Intent(context, ListaDietasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.find_dietas:
                        intent = new Intent(context, FiltroDietasActivity.class);
                        startActivity(intent);
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
        MenuItem menuItem;
        public LoadImage(MenuItem menuItem) {
            this.menuItem= menuItem;
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
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            menuItem.setIcon(d);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_app_bar, menu);
        SQLiteDatabase db = Conexion.getInstance(getContext());
        UsuarioDAOImpl userDao = new UsuarioDAOImpl(db);
        try {
            user = userDao.findById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String src = user.getSrc();
        MenuItem menuItem = menu.findItem(R.id.ic_showuser);
        LoadImage loadimage = new LoadImage(menuItem);
        loadimage.execute(src);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
