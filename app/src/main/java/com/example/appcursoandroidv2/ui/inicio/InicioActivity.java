package com.example.appcursoandroidv2.ui.inicio;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.UsuarioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.vm.UsuarioViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class InicioActivity extends AppCompatActivity {
    Usuario user;
    TextView tvWelcome;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        toolbar = findViewById(R.id.topAppBar);
        toolbar.setTitle(R.string.inicio);
        SQLiteDatabase db = Conexion.getInstance(InicioActivity.this);
        UsuarioDAOImpl userDao = new UsuarioDAOImpl(db);
        try {
            user = userDao.findById(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UsuarioViewModel model = new ViewModelProvider(this).get(UsuarioViewModel.class);
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
