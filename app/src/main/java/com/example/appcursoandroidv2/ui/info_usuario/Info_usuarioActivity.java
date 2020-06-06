package com.example.appcursoandroidv2.ui.info_usuario;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.inicio.InicioViewModel;

public class Info_usuarioActivity extends AppCompatActivity {
    Usuario user;
    TextView tvNombreApellido;
    TextView tvUserName;
    TextView tvDni;
    TextView tvLastConection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        tvNombreApellido= findViewById(R.id.tv_nombre_apellido);
        tvUserName= findViewById(R.id.tv_username);
        tvDni= findViewById(R.id.tv_dni);
        tvLastConection= findViewById(R.id.tv_last_conection);
        InicioViewModel model = new ViewModelProvider(this).get(InicioViewModel.class);
        Bundle bundle= getIntent().getExtras();
        if(bundle != null){
             user = (Usuario) bundle.getSerializable("usuario");
            model.setUser(user);
        }
        model.getUser().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario user) {
                tvNombreApellido.setText(user.getNameSurname());
                tvUserName.setText(user.getUserName());
                tvDni.setText(user.getDni());
                tvLastConection.setText(user.getLastConection());
            }
        });
    }
}
