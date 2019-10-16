package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.GenreDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.Genre;

import java.util.List;

public class GenreServiceImpl implements Service<Genre> {

    @Override
    public void create(Genre genre) {
        new GenreDAOImpl().add(genre);
    }

    @Override
    public List<Genre> getAll() {
        return new GenreDAOImpl().fetchAll();
    }

    @Override
    public Genre getById(int id) {
        return new GenreDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new GenreDAOImpl().delete(id);
    }

    @Override
    public void update(Genre genre) {
        new GenreDAOImpl().update(genre);
    }
}
