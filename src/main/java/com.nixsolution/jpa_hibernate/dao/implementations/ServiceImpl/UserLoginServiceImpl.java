package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.UserLoginDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.UserLogin;

import java.util.List;

public class UserLoginServiceImpl implements Service<UserLogin> {
    @Override
    public void create(UserLogin userLogin) {
        new UserLoginDAOImpl().add(userLogin);
    }

    @Override
    public List<UserLogin> getAll() {
        return new UserLoginDAOImpl().fetchAll();

    }

    @Override
    public UserLogin getById(int id) {
        return new UserLoginDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new UserLoginDAOImpl().delete(id);

    }

    @Override
    public void update(UserLogin userLogin) {
        new UserLoginDAOImpl().update(userLogin);
    }
}
