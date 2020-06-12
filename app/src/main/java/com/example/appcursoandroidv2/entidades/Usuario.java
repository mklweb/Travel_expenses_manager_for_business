package com.example.appcursoandroidv2.entidades;

import java.io.Serializable;

public class Usuario implements Serializable {
    private long id;
    private String nameSurname;
    private String password;
    private String dni;
    private long lastConection;//Milisegundos desde 1/1/1970
    private long currentConection;//Milisegundos desde 1/1/1970
    private String userName;
    private String src;

    public Usuario(){
    }

    public Usuario(String nameSurname, String password, String dni, long lastConection,long currentConection, String userName) {
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

    public long getLastConection() {
        return lastConection;
    }

    public void setLastConection(long lastConection) {
        this.lastConection = lastConection;
    }

    public long getCurrentConection() {return currentConection;}

    public void setCurrentConection(long currentConection) {this.currentConection = currentConection;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
