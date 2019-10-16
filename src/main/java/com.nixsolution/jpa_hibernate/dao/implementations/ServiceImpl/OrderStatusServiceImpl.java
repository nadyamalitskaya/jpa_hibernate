package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.OrderStatusDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.OrderStatus;

import java.util.List;

public class OrderStatusServiceImpl implements Service<OrderStatus> {
    @Override
    public void create(OrderStatus orderStatus) {
        new OrderStatusDAOImpl().add(orderStatus);
    }

    @Override
    public List<OrderStatus> getAll() {
        return new OrderStatusDAOImpl().fetchAll();
    }

    @Override
    public OrderStatus getById(int id) {
        return new OrderStatusDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new OrderStatusDAOImpl().delete(id);
    }

    @Override
    public void update(OrderStatus orderStatus) {
        new OrderStatusDAOImpl().update(orderStatus);
    }
}
