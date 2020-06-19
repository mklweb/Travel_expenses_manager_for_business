package com.example.appcursoandroidv2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_GASTO = new StringBuilder().
            append("CREATE TABLE " + Constantes.TABLA_GASTO + "(").
            append(Constantes.GASTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,").
            append(Constantes.GASTO_FECHA + " REAL NOT NULL,").
            append(Constantes.GASTO_TRANSPORTE + " REAL,").
            append(Constantes.GASTO_KILOMETRAJE + " REAL,").
            append(Constantes.GASTO_PRECIO_KM + " REAL,").
            append(Constantes.GASTO_PEAJE + " REAL,").
            append(Constantes.GASTO_PARKING + " REAL,").
            append(Constantes.GASTO_RESTAURANTE + " REAL,").
            append(Constantes.GASTO_OTROS + " REAL,").
            append(Constantes.GASTO_DEP + " TEXT,").
            append(Constantes.GASTO_PRO + " TEXT)").
            toString();

    private static final String SQL_CREATE_TABLE_DIETA = new StringBuilder().
            append("CREATE TABLE " + Constantes.TABLA_DIETA + "(").
            append(Constantes.DIETA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,").
            append(Constantes.DIETA_FECHA_INI + " REAL NOT NULL,").
            append(Constantes.DIETA_FECHA_FIN + " REAL NOT NULL,").
            append(Constantes.DIETA_PAIS + " TEXT NOT NULL,").
            append(Constantes.DIETA_CIUDAD + " TEXT NOT NULL,").
            append(Constantes.DIETA_DIETA + " REAL)").
            toString();

    private static final String SQL_CREATE_TABLE_PRECIO = new StringBuilder().
            append("CREATE TABLE " + Constantes.TABLA_PRECIO + "(").
            append(Constantes.PRECIO_DIETA_EU + " REAL NOT NULL,").
            append(Constantes.PRECIO_DIETA_RESTO + " REAL NOT NULL,").
            append(Constantes.PRECIO_KM + " REAL NOT NULL)").
            toString();

    private static final String SQL_CREATE_TABLE_USUARIO = new StringBuilder()
            .append("CREATE TABLE " + Constantes.TABLA_USUARIO + "(")
            .append(Constantes.USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,")
            .append(Constantes.USUARIO_DNI + " TEXT NOT NULL,")
            .append(Constantes.USUARIO_NOMBRE + " TEXT NOT NULL UNIQUE,")
            .append(Constantes.USUARIO_PASSWORD + " TEXT NOT NULL,")
            .append(Constantes.USUARIO_ALIAS + " TEXT NOT NULL, ")
            .append(Constantes.USUARIO_SRC + " TEXT NOT NULL, ")
            .append(Constantes.USUARIO_LAST_CONNECTION + " REAL,")
            .append(Constantes.USUARIO_CURRENT_CONNECTION + " REAL)")
            .toString();

    public DBOpenHelper(@Nullable Context context) {
        super(context, Constantes.DB_NAME, null, Constantes.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_GASTO);
        db.execSQL(SQL_CREATE_TABLE_DIETA);
        db.execSQL(SQL_CREATE_TABLE_PRECIO);
        db.execSQL(SQL_CREATE_TABLE_USUARIO);
        db.execSQL("INSERT INTO precio ('dieta_eu', 'dieta_resto', 'precio_km') values (60, 100, 0.3)");
        db.execSQL("INSERT INTO  usuario ("
                + Constantes.USUARIO_ID + ","
                + Constantes.USUARIO_DNI + ","
                + Constantes.USUARIO_NOMBRE + ","
                + Constantes.USUARIO_PASSWORD + ","
                + Constantes.USUARIO_ALIAS + ","
                + Constantes.USUARIO_SRC + ","
                + Constantes.USUARIO_LAST_CONNECTION + ","
                + Constantes.USUARIO_CURRENT_CONNECTION
                + ") values(1, '12345678N', 'Patxi', '1', '1', 'https://mikelweb.ml/img/foto_freddieXL.png'," + (new Date().getTime() - 86400000) + "," + new Date().getTime() + ")");
        db.execSQL(
                "INSERT INTO gasto (fecha,transporte, kilometraje, precio_km, peaje, parking, restaurante, otros, pro, dep)\n" +
                "VALUES \n" +
                    "(1552867200000,100,'',0.3,'','',50,'','ABC',''),\n" +
                    "(1556064000000,'',300,0.3,16,15,40,'','CCC',''),\n" +
                    "(1559520000000,200,'','',16,15,40,'','CCC',''),\n" +
                    "(1571011200000,'',300,0.3,16,15,40,'','CCC',''),\n" +
                    "(1578528000000,150,'','','','',80,'','CCC',''),\n" +
                    "(1595289600000,'',400,0.3,10,13,60,'','ZX','');");
        db.execSQL(
                ""
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * método al que se le llama cada vez que hay una
         * actualización en la base de datos (modificar la estructura,
         * agregar restricciones a la base de datos, ...).
         * */

        db.execSQL("DROP TABLE IF EXISTS gasto");
        db.execSQL("DROP TABLE IF EXISTS dieta");
        db.execSQL("DROP TABLE IF EXISTS precio");
        db.execSQL("DROP TABLE IF EXISTS usuario");

        //Se crea la nueva versión de la base de datos
        onCreate(db);

    }

}
