package com.example.appcursoandroidv2.database;

public class Constantes {

    public static final String DB_NAME = "viajes";
    public static final int DB_VERSION = 1;

    public static final String TABLA_GASTO = "gasto";
    public static final String GASTO_ID = "id";
    public static final String GASTO_FECHA = "fecha";
    public static final String GASTO_TRANSPORTE = "transporte";
    public static final String GASTO_KILOMETRAJE = "kilometraje";
    public static final String GASTO_PEAJE = "peaje";
    public static final String GASTO_PARKING = "parking";
    public static final String GASTO_RESTAURANTE = "restaurante";
    public static final String GASTO_OTROS = "otros";
    public static final String GASTO_PRECIO_KM = "precio_km";
    public static final String GASTO_PRO = "pro";
    public static final String GASTO_DEP = "dep";

    public static final String TABLA_DIETA = "dieta";
    public static final String DIETA_ID = "id";
    public static final String DIETA_FECHA_INI = "fecha_ini";
    public static final String DIETA_FECHA_FIN = "fecha_fin";
    public static final String DIETA_PAIS = "pais";
    public static final String DIETA_CIUDAD = "ciudad";
    public static final String DIETA_DIETA = "dieta";

    public static final String TABLA_PRECIO = "precio";
    public static final String PRECIO_DIETA_EU = "dieta_eu";
    public static final String PRECIO_DIETA_RESTO = "dieta_resto";
    public static final String PRECIO_KM = "precio_km";

    public static final String TABLA_USUARIO = "usuario";
    public static final String USUARIO_ID = "id";
    public static final String USUARIO_DNI = "dni";
    public static final String USUARIO_NOMBRE = "nombre";
    public static final String USUARIO_PASSWORD = "password";
    public static final String USUARIO_LAST_CONNECTION = "last_conn";
    public static final String USUARIO_CURRENT_CONNECTION = "current_conn";
}
