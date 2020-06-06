package com.example.appcursoandroidv2.dao;

import java.util.List;

public interface DAO<T>{
    long add(T t) throws Exception;
    int remove(String id) throws Exception;
    int modify(T t) throws Exception;
    T findById(long id) throws Exception;
    List<T> findAll() throws Exception;
}
