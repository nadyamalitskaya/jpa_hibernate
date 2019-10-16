package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.UserRole;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class UserRoleDAOImpl implements DAOInterface<UserRole> {

    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(UserRole userRole) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(userRole);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<UserRole> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserRole> cr = cb.createQuery(UserRole.class);
            Root<UserRole> root = cr.from(UserRole.class);
            cr.select(root);
            Query<UserRole> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public UserRole fetchById(int id) {
        UserRole userRole;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserRole> criteriaQuery = cb
                    .createQuery(UserRole.class);
            Root<UserRole> root = criteriaQuery.from(UserRole.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<UserRole> query = session.createQuery(criteriaQuery);
            userRole = query.getSingleResult();
        }
        return userRole;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<UserRole> criteriaDelete = cb
                    .createCriteriaDelete(UserRole.class);
            Root<UserRole> root = criteriaDelete.from(UserRole.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(UserRole userRole) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<UserRole> criteriaUpdate = cb
                    .createCriteriaUpdate(UserRole.class);
            Root<UserRole> root = criteriaUpdate.from(UserRole.class);
            criteriaUpdate.set("roleName", userRole.getRoleName());
            criteriaUpdate.where(cb.equal(root.get("id"), userRole.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
