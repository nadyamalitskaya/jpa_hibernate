package com.nixsolution.jpa_hibernate.dao.interfaces;

import java.util.List;

public interface Service<T> {

    void create(T t);

    List<T> getAll();

    T getById(int id);

    void delete(int id);

    void update(T t);
}
