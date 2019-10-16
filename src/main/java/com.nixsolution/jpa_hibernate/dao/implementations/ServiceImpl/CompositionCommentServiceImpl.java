package com.nixsolution.jpa_hibernate.dao.implementations.ServiceImpl;

import com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl.CompositionCommentDAOImpl;
import com.nixsolution.jpa_hibernate.dao.interfaces.Service;
import com.nixsolution.jpa_hibernate.entity.CompositionComment;

import java.util.List;

public class CompositionCommentServiceImpl implements Service<CompositionComment> {

    @Override
    public void create(CompositionComment compositionComment) {
        new CompositionCommentDAOImpl().add(compositionComment);
    }

    @Override
    public List<CompositionComment> getAll() {
        return new CompositionCommentDAOImpl().fetchAll();
    }

    @Override
    public CompositionComment getById(int id) {
        return new CompositionCommentDAOImpl().fetchById(id);
    }

    @Override
    public void delete(int id) {
        new CompositionCommentDAOImpl().delete(id);
    }

    @Override
    public void update(CompositionComment compositionComment) {
        new CompositionCommentDAOImpl().update(compositionComment);
    }
}
