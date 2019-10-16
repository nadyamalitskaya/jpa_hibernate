package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.UserLogin;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class UserLoginDAOImpl implements DAOInterface<UserLogin> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(UserLogin userLogin) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(userLogin);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<UserLogin> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserLogin> cr = cb.createQuery(UserLogin.class);
            Root<UserLogin> root = cr.from(UserLogin.class);
            cr.select(root);
            Query<UserLogin> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public UserLogin fetchById(int id) {
        UserLogin userLogin;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserLogin> criteriaQuery = cb
                    .createQuery(UserLogin.class);
            Root<UserLogin> root = criteriaQuery.from(UserLogin.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<UserLogin> query = session.createQuery(criteriaQuery);
            userLogin = query.getSingleResult();
        }
        return userLogin;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<UserLogin> criteriaDelete = cb
                    .createCriteriaDelete(UserLogin.class);
            Root<UserLogin> root = criteriaDelete.from(UserLogin.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(UserLogin userLogin) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<UserLogin> criteriaUpdate = cb
                    .createCriteriaUpdate(UserLogin.class);
            Root<UserLogin> root = criteriaUpdate.from(UserLogin.class);
            criteriaUpdate.set("loginName", userLogin.getLoginName());
            criteriaUpdate.set("password", userLogin.getPassword());
            criteriaUpdate.set("email", userLogin.getEmail());
            criteriaUpdate.where(cb.equal(root.get("id"), userLogin.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
