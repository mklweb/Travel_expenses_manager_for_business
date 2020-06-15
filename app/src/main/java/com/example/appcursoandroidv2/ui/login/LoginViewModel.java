package com.example.appcursoandroidv2.ui.login;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.appcursoandroidv2.R;
import com.example.appcursoandroidv2.dao.UsuarioDAOImpl;
import com.example.appcursoandroidv2.database.Conexion;
import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class LoginViewModel extends ViewModel {
    private LoginModel loginModel;
    private LoginCallbacks loginCallbacks;
    static String faltanCampos= "Introduzca ambos campos";
    static String noAuth= "Usuario no encontrado o contraseña incorrecta";
    Usuario user;
    Context context;
    //UserDaoImpl userDaoImpl;
    public LoginViewModel(){
    }
    public LoginViewModel(LoginCallbacks loginCallbacks) {
        this.loginModel = new LoginModel();
        this.loginCallbacks = loginCallbacks;
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public LoginCallbacks getLoginCallbacks() {
        return loginCallbacks;
    }

    public void setLoginCallbacks(LoginCallbacks loginCallbacks) {
        this.loginCallbacks = loginCallbacks;
    }
    public TextWatcher nombreTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                loginModel.setNombre(s.toString());
            }
        };
    }

    public TextWatcher passwordTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                loginModel.setPassword(s.toString());
            }
        };
    }
    public void onLoginBtnClick (View view){

        if (loginModel.isValid()) {

            SQLiteDatabase db = Conexion.getInstance(view.getContext());
            UsuarioDAOImpl userDao = new UsuarioDAOImpl(db);
                try {
                    user = userDao.findByName(loginModel.getNombre());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            if (user == null){
                loginCallbacks.showError(noAuth);
            }else{
                //user = new Usuario( "Patxi", "1", "12345678N","2020-02-04 08:33:53","2020-06-04 08:33:53", "1");
                if (user.getPassword().equals(loginModel.getPassword()) && user.getNameSurname().equals(loginModel.getNombre())){
                    user.setLastConection(user.getCurrentConection());
                    Date date = new Date();

                    user.setCurrentConection (date.getTime());
                    context=view.getContext();
                    Intent sendIntent= new Intent(context, InicioActivity.class);
                    context.startActivity(sendIntent);
//                itlNombre.setError(null);
//                itlPass.setError(null);

                }else{
                    loginCallbacks.showError(noAuth);
//                itlNombre.setError("Mal nombre");
//                itlPass.setError("Mal Password");
                }
            }

        }else{
//            itlNombre.setError("Falta nombre");
//            itlPass.setError("Falta Password");
            loginCallbacks.showError(faltanCampos);
        }
    }
}
