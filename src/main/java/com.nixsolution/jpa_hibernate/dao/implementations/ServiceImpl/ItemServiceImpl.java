package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.ItemDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.Item;
import org.hibernate.Session;

import java.util.List;

public class ItemServiceImpl implements Service<Item> {

    @Override
    public void create(Item item) {
        new ItemDAOImpl().add(item);
    }

    @Override
    public List<Item> getAll() {
        return new ItemDAOImpl().fetchAll();
    }

    @Override
    public Item getById(int id) {
        return new ItemDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new ItemDAOImpl().delete(id);
    }

    @Override
    public void update(Item item) {
        new ItemDAOImpl().update(item);
    }
}
