package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.BookDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.Book;

import java.util.List;

public class BookServiceImpl implements Service<Book> {

    @Override
    public void create(Book book) {
        new BookDAOImpl().add(book);
    }

    @Override
    public List<Book> getAll() {
        return new BookDAOImpl().fetchAll();
    }

    @Override
    public Book getById(int id) {
        return new BookDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new BookDAOImpl().delete(id);
    }

    @Override
    public void update(Book book) {
        new BookDAOImpl().update(book);
    }
}
