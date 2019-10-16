package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.ItemStatusDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.ItemStatus;

import java.util.List;

public class ItemStatusServiceImpl implements Service<ItemStatus> {
    @Override
    public void create(ItemStatus itemStatus) {
        new ItemStatusDAOImpl().add(itemStatus);
    }

    @Override
    public List<ItemStatus> getAll() {
        return new ItemStatusDAOImpl().fetchAll();
    }

    @Override
    public ItemStatus getById(int id) {
        return new ItemStatusDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new ItemStatusDAOImpl().delete(id);
    }

    @Override
    public void update(ItemStatus itemStatus) {
        new ItemStatusDAOImpl().update(itemStatus);
    }
}
