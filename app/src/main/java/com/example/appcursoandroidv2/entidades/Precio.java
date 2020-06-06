package com.example.appcursoandroidv2.entidades;

import java.io.Serializable;

public class Precio implements Serializable {
    private double dietaEu;
    private double dietaRes;
    private double precioKM;

    public Precio() {
    }

    public Precio(double dietaEu, double dietaRes, double precioKM) {
        this.dietaEu = dietaEu;
        this.dietaRes = dietaRes;
        this.precioKM = precioKM;
    }

    public double getDietaEu() {
        return dietaEu;
    }

    public void setDietaEu(double dietaEu) {
        this.dietaEu = dietaEu;
    }

    public double getDietaRes() {
        return dietaRes;
    }

    public void setDietaRes(double dietaRes) {
        this.dietaRes = dietaRes;
    }

    public double getPrecioKM() {
        return precioKM;
    }

    public void setPrecioKM(double precioKM) {
        this.precioKM = precioKM;
    }
}
