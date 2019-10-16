package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.UserOrderDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.UserOrder;

import java.util.List;

public class UserOrderServiceImpl implements Service<UserOrder> {
    @Override
    public void create(UserOrder userOrder) {
        new UserOrderDAOImpl().add(userOrder);
    }

    @Override
    public List<UserOrder> getAll() {
        return new UserOrderDAOImpl().fetchAll();
    }

    @Override
    public UserOrder getById(int id) {
        return new UserOrderDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new UserOrderDAOImpl().delete(id);
    }

    @Override
    public void update(UserOrder userRole) {
        new UserOrderDAOImpl().update(userRole);
    }
}
