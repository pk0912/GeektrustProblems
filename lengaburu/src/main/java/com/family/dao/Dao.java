package com.family.dao;

import java.util.List;

public interface Dao<T> {

    T get(String name);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);

}
