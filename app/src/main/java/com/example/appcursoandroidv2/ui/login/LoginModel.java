package com.example.appcursoandroidv2.ui.login;

import android.text.TextUtils;

import androidx.annotation.Nullable;

public class LoginModel {
    @Nullable
     String nombre, password;

    public LoginModel() {
    }

    public LoginModel(@Nullable String nombre, @Nullable String password) {
        this.nombre = nombre;
        this.password = password;
    }

    @Nullable
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@Nullable String nombre) {
        this.nombre = nombre;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }
    public boolean isValid(){
        return !TextUtils.isEmpty(nombre) && !TextUtils.isEmpty(password);
    }

}
