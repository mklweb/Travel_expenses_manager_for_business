package com.example.appcursoandroidv2.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {
    private long id;
    private String nameSurname;
    private String password;
    private String dni;
    private String lastConection;//Date
    private String currentConection;//Date
    private String userName;
    private String src;

    public Usuario(){
    }

    public Usuario(String nameSurname, String password, String dni, String lastConection,String currentConection, String userName) {
        this.nameSurname = nameSurname;
        this.password = password;
        this.dni = dni;
        this.lastConection = lastConection;
        this.currentConection = currentConection;
        this.userName = userName;
        this.src = src;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLastConection() {
        return lastConection;
    }

    public void setLastConection(String lastConection) {
        this.lastConection = lastConection;
    }

    public String getCurrentConection() {return currentConection;}

    public void setCurrentConection(String currentConection) {this.currentConection = currentConection;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
