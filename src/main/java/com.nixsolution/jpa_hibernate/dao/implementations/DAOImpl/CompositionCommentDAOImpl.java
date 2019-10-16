package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.CompositionComment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class CompositionCommentDAOImpl
        implements DAOInterface<CompositionComment> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(CompositionComment compositionComment) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(compositionComment);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<CompositionComment> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CompositionComment> cr = cb.createQuery(CompositionComment.class);
            Root<CompositionComment> root = cr.from(CompositionComment.class);
            cr.select(root);
            Query<CompositionComment> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public CompositionComment fetchById(int id) {
        CompositionComment compositionComment;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CompositionComment> criteriaQuery = cb
                    .createQuery(CompositionComment.class);
            Root<CompositionComment> root = criteriaQuery.from(CompositionComment.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<CompositionComment> query = session.createQuery(criteriaQuery);
            compositionComment = query.getSingleResult();
        }
        return compositionComment;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<CompositionComment> criteriaDelete = cb
                    .createCriteriaDelete(CompositionComment.class);
            Root<CompositionComment> root = criteriaDelete.from(CompositionComment.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(CompositionComment compositionComment) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<CompositionComment> criteriaUpdate = cb
                    .createCriteriaUpdate(CompositionComment.class);
            Root<CompositionComment> root = criteriaUpdate.from(CompositionComment.class);
            criteriaUpdate.set("comment", compositionComment.getComment());
            criteriaUpdate.set("commentDate", compositionComment.getCommentDate());
            criteriaUpdate.set("user", compositionComment.getUser());
            criteriaUpdate.set("book", compositionComment.getBook());
            criteriaUpdate.where(cb.equal(root.get("id"), compositionComment.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
