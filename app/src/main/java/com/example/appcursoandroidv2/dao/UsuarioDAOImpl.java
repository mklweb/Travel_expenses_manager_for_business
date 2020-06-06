package com.example.appcursoandroidv2.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcursoandroidv2.database.Constantes;
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
        return 0;
    }

    @Override
    public Usuario findById(long id) throws Exception {
        return null;
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
        usuario.setUserName( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_NOMBRE) ) );
        usuario.setPassword( cursor.getString(cursor.getColumnIndex(Constantes.USUARIO_PASSWORD) ) );
        return usuario;
    }
}
