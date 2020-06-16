package com.example.appcursoandroidv2.entidades;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class LoginModel implements Serializable {
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


}
