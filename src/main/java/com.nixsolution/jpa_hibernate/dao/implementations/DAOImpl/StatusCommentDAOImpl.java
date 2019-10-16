package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.StatusComment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class StatusCommentDAOImpl implements DAOInterface<StatusComment> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(StatusComment statusComment) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(statusComment);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<StatusComment> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<StatusComment> cr = cb.createQuery(StatusComment.class);
            Root<StatusComment> root = cr.from(StatusComment.class);
            cr.select(root);
            Query<StatusComment> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public StatusComment fetchById(int id) {
        StatusComment statusComment;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<StatusComment> criteriaQuery = cb
                    .createQuery(StatusComment.class);
            Root<StatusComment> root = criteriaQuery.from(StatusComment.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<StatusComment> query = session.createQuery(criteriaQuery);
            statusComment = query.getSingleResult();
        }
        return statusComment;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<StatusComment> criteriaDelete = cb
                    .createCriteriaDelete(StatusComment.class);
            Root<StatusComment> root = criteriaDelete.from(StatusComment.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(StatusComment userRole) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<StatusComment> criteriaUpdate = cb
                    .createCriteriaUpdate(StatusComment.class);
            Root<StatusComment> root = criteriaUpdate.from(StatusComment.class);
            criteriaUpdate.set("comment", userRole.getComment());
            criteriaUpdate.set("commentDate", userRole.getCommentDate());
            criteriaUpdate.set("item", userRole.getItem());
            criteriaUpdate.set("systemUser", userRole.getSystemUser());
            criteriaUpdate.where(cb.equal(root.get("id"), userRole.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
