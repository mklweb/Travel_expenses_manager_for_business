package com.example.appcursoandroidv2.dao;

import com.example.appcursoandroidv2.entidades.Usuario;

public interface UsuarioDAO extends DAO<Usuario> {

    Usuario findByName(String name) throws Exception;

}
