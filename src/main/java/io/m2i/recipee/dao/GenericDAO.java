package io.m2i.recipee.dao;

import java.util.List;

public interface GenericDAO<T, ID> {

    T create(T entity);

    List<T> findAll();

    T getById(ID id);
    boolean update(T entity);

    boolean delete(T entity);

}
