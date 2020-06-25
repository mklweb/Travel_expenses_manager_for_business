package com.example.appcursoandroidv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

import com.example.appcursoandroidv2.dao.UsuarioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.LoginModel;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.example.appcursoandroidv2.ui.vm.LoginViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LoginViewModel model;
    LoginModel login;
    EditText etNombre, etPass;
    TextInputLayout itlNombre, itlPass;
    Usuario user;
    Button btnLogin;
    Toolbar toolbar;
    View view;
    String VERSION_TITLE = "Versión";
    String VERSION = "1.0.0";
    String NO_DATA = "Introduzca datos";
    String WRONG_DATA = "Datos incorrectos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewConfig();
        model = new ViewModelProvider(this).get(LoginViewModel.class);
        login= new LoginModel();
        setToolbar();
        addTextWatchers();
        addOnclickListeners();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.mn_about);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                view = item.getActionView();
                switch (id) {
                    case R.id.mn_about:
                        showDialog(view);
                        break;
                }
                return false;
            }
        });
        return true;
    }
    public void setToolbar (){
        toolbar = findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(R.string.maristak);

    }
    public boolean isNombreValid(){
        return !TextUtils.isEmpty(login.getNombre());
    }
    public boolean isPassValid(){
        return  !TextUtils.isEmpty(login.getPassword());
    }
    public void viewConfig (){
        etNombre = findViewById(R.id.et_nombre);
        etPass = findViewById(R.id.et_pass);
        itlNombre = findViewById(R.id.itl_nombre);
        itlPass =findViewById(R.id.itl_pass);
        btnLogin = findViewById(R.id.bt_send_login);
    }
    public void addTextWatchers (){
        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                login.setNombre(s.toString());

            }
        });
        etPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                login.setPassword(s.toString());

            }
        });

        model.getLogin().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel login) {
                etNombre.setText(login.getNombre());
                etPass.setText(login.getPassword());
            }
        });
    }
    public  void addOnclickListeners(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNombreValid() && isPassValid()) {
                    SQLiteDatabase db = Conexion.getInstance(MainActivity.this);
                    UsuarioDAOImpl userDao = new UsuarioDAOImpl(db);
                    try {
                        user = userDao.findByName(login.getNombre());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (user == null){
                        itlNombre.setError(WRONG_DATA);
                        itlPass.setError(WRONG_DATA);
                    }else{

                        if (user.getPassword().equals(login.getPassword()) && user.getUserName().equals(login.getNombre())){
                            user.setLastConection(user.getCurrentConection());
                            Date date = new Date();
                            user.setCurrentConection (date.getTime());
                            try {
                                userDao.modify(user);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            Intent sendIntent= new Intent(MainActivity.this, InicioActivity.class);
                            startActivity(sendIntent);
                            itlNombre.setError(null);
                            itlPass.setError(null);
                        }else{

                            itlNombre.setError(WRONG_DATA);
                            itlPass.setError(WRONG_DATA);
                        }
                    }
                }else{
                    if(!isNombreValid()){
                        itlNombre.setError(NO_DATA);
                    }else{
                        itlNombre.setError(null);
                    }
                    if(!isPassValid()){
                        itlPass.setError(NO_DATA);
                    }else{
                        itlPass.setError(null);
                    }
                }
            }
        });
    }
    public void showDialog(View view) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setTitle(VERSION_TITLE);
        builder.setMessage(VERSION);
        builder.show();
    }
}

