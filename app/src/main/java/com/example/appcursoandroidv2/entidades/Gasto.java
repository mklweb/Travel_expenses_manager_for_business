package com.example.appcursoandroidv2.entidades;

import java.io.Serializable;

public class Gasto implements Serializable {

    private long id;
    private long fecha;
    private double transporte;
    private double kilometraje;
    private double peaje;
    private double parking;
    private double restaurante;
    private double otros;
    private double precioKm;
    private String pro = "";
    private String dep = "";

    public Gasto() {
    }

    public Gasto(long id, long fecha, double transporte, double kilometraje, double peaje, double parking, double restaurante, double otros) {
        this.id = id;
        this.fecha = fecha;
        this.transporte = transporte;
        this.kilometraje = kilometraje;
        this.peaje = peaje;
        this.parking = parking;
        this.restaurante = restaurante;
        this.otros = otros;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public double getTransporte() {
        return transporte;
    }

    public void setTransporte(double transporte) {
        this.transporte = transporte;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public double getPeaje() {
        return peaje;
    }

    public void setPeaje(double peaje) {
        this.peaje = peaje;
    }

    public double getParking() {
        return parking;
    }

    public void setParking(double parking) {
        this.parking = parking;
    }

    public double getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(double restaurante) {
        this.restaurante = restaurante;
    }

    public double getOtros() {
        return otros;
    }

    public void setOtros(double otros) {
        this.otros = otros;
    }

    public double getPrecioKm() {
        return precioKm;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setPrecioKm(double precioKm) {
        this.precioKm = precioKm;
    }

    public String getTotal() {
        double total = transporte + kilometraje * precioKm + peaje + parking + restaurante + otros;
        String totalTxt = String.format("%.2f", total);
        return totalTxt;
    }
}
