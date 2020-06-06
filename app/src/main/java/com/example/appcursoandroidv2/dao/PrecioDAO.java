package com.example.appcursoandroidv2.dao;

import com.example.appcursoandroidv2.entidades.Precio;

public interface PrecioDAO {

    String getPrecioKm() throws Exception;
    String getDietaEu() throws Exception;
    String getDietaRes() throws Exception;
    void modify(Precio precio) throws Exception;
}
