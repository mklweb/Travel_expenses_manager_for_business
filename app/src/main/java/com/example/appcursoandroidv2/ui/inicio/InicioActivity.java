package com.example.appcursoandroidv2.ui.inicio;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.UsuarioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.infoUsuario.InfoUsuarioActivity;
import com.example.appcursoandroidv2.utils.DateParser;

import java.util.Date;

public class InicioActivity extends AppCompatActivity {
    Usuario user;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        SQLiteDatabase db = Conexion.getInstance(InicioActivity.this);
        UsuarioDAOImpl userDao = new UsuarioDAOImpl(db);
        try {
            user = userDao.findByName("Patxi");
        } catch (Exception e) {
            e.printStackTrace();
        }
        InicioViewModel model = new ViewModelProvider(this).get(InicioViewModel.class);
        model.setUser(user);
        tvWelcome= findViewById(R.id.tv_welcome);
        model.getUser().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario user) {
                tvWelcome.setText(user.getNameSurname());
            }
        });

    }

}
