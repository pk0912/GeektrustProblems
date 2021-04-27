package com.ledgerCo.dao;

import java.util.HashMap;

public interface Dao<T> {

    T get(String name);

    HashMap<String, T> getAll();

    void save(T t);

}
