package com.example.appcursoandroidv2.ui.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.infoUsuario.InfoUsuarioActivity;

public class InicioActivity extends AppCompatActivity {
    Usuario user;
    TextView tvWelcome;
    Button btnProvisional;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
            user = (Usuario) bundle.getSerializable("usuario");
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
        btnProvisional = findViewById(R.id.provisional);
        btnProvisional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent= new Intent(InicioActivity.this, InfoUsuarioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", user);
                sendIntent.putExtras(bundle);
                startActivity(sendIntent);
            }
        });
    }
}
