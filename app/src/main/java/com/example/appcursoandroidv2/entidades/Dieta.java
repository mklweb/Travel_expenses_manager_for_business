package com.example.appcursoandroidv2.entidades;

public class Dieta {

    long id;
    long fechaIni;
    long fechaFin;
    String pais;
    String ciudad;
    Double dieta;

    public Dieta() {
    }

    public Dieta(long id, long fechaIni, long fechaFin, String pais, String ciudad, Double dieta) {
        this.id = id;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.pais = pais;
        this.ciudad = ciudad;
        this.dieta = dieta;
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

    public Double getDieta() {
        return dieta;
    }

    public void setDieta(Double dieta) {
        this.dieta = dieta;
    }
}
