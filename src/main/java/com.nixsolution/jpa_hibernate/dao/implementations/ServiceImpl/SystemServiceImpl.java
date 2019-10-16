package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.SystemUserDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.SystemUser;

import java.util.List;

public class SystemServiceImpl implements Service<SystemUser> {

    @Override
    public void create(SystemUser systemUser) {
        new SystemUserDAOImpl().add(systemUser);
    }

    @Override
    public List<SystemUser> getAll() {
        return new SystemUserDAOImpl().fetchAll();
    }

    @Override
    public SystemUser getById(int id) {
        return new SystemUserDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new SystemUserDAOImpl().delete(id);
    }

    @Override
    public void update(SystemUser systemUser) {
        new SystemUserDAOImpl().update(systemUser);
    }
}
