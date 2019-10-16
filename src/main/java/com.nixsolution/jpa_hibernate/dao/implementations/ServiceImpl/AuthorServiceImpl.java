package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.AuthorDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.Author;

import java.util.List;

public class AuthorServiceImpl implements Service<Author> {

    @Override
    public void create(Author author) {
        new AuthorDAOImpl().add(author);
    }

    @Override
    public List<Author> getAll() {
        return new AuthorDAOImpl().fetchAll();
    }

    @Override
    public Author getById(int id) {
        return new AuthorDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new AuthorDAOImpl().delete(id);
    }

    @Override
    public void update(Author author) {
        new AuthorDAOImpl().update(author);
    }
}
