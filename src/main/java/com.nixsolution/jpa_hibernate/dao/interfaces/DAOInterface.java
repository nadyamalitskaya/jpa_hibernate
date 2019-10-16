package com.nixsolution.jpa_hibernate.dao.interfaces;

import java.util.List;

public interface DAOInterface<T> {
    void add(T t);

    List<T> fetchAll();

    T fetchById(int id);

    void delete(int id);

    void update(T t);
}