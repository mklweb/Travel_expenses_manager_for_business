package com.example.appcursoandroidv2.entidades;

import java.io.Serializable;

public class Dieta implements Serializable {

    long id;
    long fechaIni;
    long fechaFin;
    String pais;
    String ciudad;
    double dieta;
    String proyect;
    String department;

    public Dieta() {
    }

    public Dieta(long id, long fechaIni, long fechaFin, String pais, String ciudad, Double dieta, String proyect, String department) {
        this.id = id;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.pais = pais;
        this.ciudad = ciudad;
        this.dieta = dieta;
        this.proyect = proyect;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(long fechaIni) {
        this.fechaIni = fechaIni;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getDieta() {
        return dieta;
    }

    public void setDieta(double dieta) {
        this.dieta = dieta;
    }

    public String getProyect() {return proyect;}

    public void setProyect(String proyect) {this.proyect = proyect;}

    public String getDepartment() {return department;}

    public void setDepartment(String department) {this.department = department;}

    public double getTotal() {
        long ms = getFechaFin() - getFechaIni();
        int dias;
        if(ms > 0) {
            dias = (int) (ms/(1000 * 24 * 3600)) + 1;
        } else {
            dias = 1;
        }

        double total = dias * getDieta();
        return total;
    }
}
