package com.example.appcursoandroidv2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        db.execSQL("INSERT INTO  usuario ('id', 'dni', 'nombre', 'password') values (1, '14984352X', 'Mikel Oceja', '123456')");
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

        db.execSQL("INSERT INTO precio ('dieta_eu', 'dieta_resto', 'precio_km') values (60, 100, 0.3)");
    }

}
