package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.StatusCommentDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.StatusComment;

import java.util.List;

public class StatusCommentServiceImpl implements Service<StatusComment> {
    @Override
    public void create(StatusComment statusComment) {
        new StatusCommentDAOImpl().add(statusComment);
    }

    @Override
    public List<StatusComment> getAll() {
        return new StatusCommentDAOImpl().fetchAll();
    }

    @Override
    public StatusComment getById(int id) {
        return new StatusCommentDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new StatusCommentDAOImpl().delete(id);
    }

    @Override
    public void update(StatusComment statusComment) {
        new StatusCommentDAOImpl().update(statusComment);
    }
}
