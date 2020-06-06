package com.example.appcursoandroidv2.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.appcursoandroidv2.entidades.Usuario;
import com.example.appcursoandroidv2.ui.inicio.InicioActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
            //userDaoImpl = new UserDaoImpl();
            //user = userDaoImpl.findByName(loginModel.getNombre());
            user = new Usuario( "Patxi", "1", "12345678N","2020-06-04 08:33:53","22/22/2000", "1");
            if (user.getPassword().equals(loginModel.getPassword()) && user.getUserName().equals(loginModel.getNombre())){
                user.setLastConection(user.getCurrentConection());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                user.setCurrentConection (sdf.format(c.getTime()));
                context=view.getContext();
                Intent sendIntent= new Intent(context, InicioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario", user);
                sendIntent.putExtras(bundle);
                context.startActivity(sendIntent);
                }else{
                loginCallbacks.showError(noAuth);
            }
        }else{
            loginCallbacks.showError(faltanCampos);
        }
    }
}
