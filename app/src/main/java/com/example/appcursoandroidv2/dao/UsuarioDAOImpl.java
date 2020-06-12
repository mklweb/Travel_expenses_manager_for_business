package com.example.appcursoandroidv2.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcursoandroidv2.database.Constantes;
import com.example.appcursoandroidv2.entidades.Gasto;
import com.example.appcursoandroidv2.entidades.Usuario;

import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    SQLiteDatabase db;

    private static final String[] COLUMNS = {
            Constantes.USUARIO_ID,
            Constantes.USUARIO_DNI,
            Constantes.USUARIO_NOMBRE,
            Constantes.USUARIO_PASSWORD,
            Constantes.USUARIO_CURRENT_CONNECTION,
            Constantes.USUARIO_CURRENT_CONNECTION};

    public UsuarioDAOImpl(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public long add(Usuario usuario) throws Exception {
        return 0;
    }

    @Override
    public int remove(String id) throws Exception {
        return 0;
    }

    @Override
    public int modify(Usuario usuario) throws Exception {
        String[] args = new String[]{String.valueOf(usuario.getId())};
        return db.update(Constantes.TABLA_GASTO, getContentValues(usuario), Constantes.GASTO_ID + "=?", args);
    }

    @Override
    public Usuario findById(long id) throws Exception {
        String qry = "SELECT * FROM " + Constantes.TABLA_USUARIO + " WHERE " + Constantes.USUARIO_ID + "=" + id;
        Cursor cursor = db.rawQuery(qry, null);
        return getResultOne(cursor);
    }

    @Override
    public List<Usuario> findAll() throws Exception {
        return null;
    }

    @Override
    public Usuario findByName(String name) throws Exception{
        String[] args = new String[] {name};
        String qry = "SELECT * FROM " + Constantes.TABLA_USUARIO + " WHERE " + Constantes.USUARIO_NOMBRE + "=?";
        Cursor cursor = db.rawQuery(qry, args);
        Usuario usuario= getResultOne(cursor);
        return usuario;
    }

    private Usuario getResultOne(Cursor cursor) throws Exception{
        if (cursor.moveToNext()){
            Usuario usuario = new Usuario();
            getNextCursor(cursor, usuario);
            return usuario;
        } else {
            throw new Exception("No existe el registro");
        }
    }

    private Usuario getNextCursor(Cursor cursor, Usuario usuario) {
        usuario.setId( cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_ID) ) );
        usuario.setDni( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_DNI) ) );
        usuario.setNameSurname( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_NOMBRE) ) );
        usuario.setUserName( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_ALIAS) ) );
        usuario.setPassword( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_PASSWORD) ) );
        usuario.setSrc( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_SRC) ) );
        usuario.setLastConection( cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_LAST_CONNECTION) ) );
        usuario.setCurrentConection( cursor.getLong(cursor.getColumnIndex(Constantes.USUARIO_CURRENT_CONNECTION) ) );

        return usuario;
    }

    private ContentValues getContentValues(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constantes.USUARIO_CURRENT_CONNECTION, usuario.getCurrentConection());
        contentValues.put(Constantes.USUARIO_LAST_CONNECTION, usuario.getLastConection());
        return contentValues;
    }
}
