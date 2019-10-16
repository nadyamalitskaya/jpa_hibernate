package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.UserRoleDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.UserRole;

import java.util.List;

public class UserRoleServiceImpl implements Service<UserRole> {

    @Override
    public void create(UserRole userRole) {
        new UserRoleDAOImpl().add(userRole);
    }

    @Override
    public List<UserRole> getAll() {
        return new UserRoleDAOImpl().fetchAll();
    }

    @Override
    public UserRole getById(int id) {
        return new UserRoleDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new UserRoleDAOImpl().delete(id);
    }

    @Override
    public void update(UserRole userRole) {
        new UserRoleDAOImpl().update(userRole);
    }
}
