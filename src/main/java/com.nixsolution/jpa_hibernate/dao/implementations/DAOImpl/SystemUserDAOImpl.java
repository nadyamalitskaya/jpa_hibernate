package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.SystemUser;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class SystemUserDAOImpl implements DAOInterface<SystemUser> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(SystemUser systemUser) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(systemUser);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<SystemUser> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<SystemUser> cr = cb.createQuery(SystemUser.class);
            Root<SystemUser> root = cr.from(SystemUser.class);
            cr.select(root);
            Query<SystemUser> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public SystemUser fetchById(int id) {
        SystemUser systemUser;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<SystemUser> criteriaQuery = cb
                    .createQuery(SystemUser.class);
            Root<SystemUser> root = criteriaQuery.from(SystemUser.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<SystemUser> query = session.createQuery(criteriaQuery);
            systemUser = query.getSingleResult();
        }
        return systemUser;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<SystemUser> criteriaDelete = cb
                    .createCriteriaDelete(SystemUser.class);
            Root<SystemUser> root = criteriaDelete.from(SystemUser.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(SystemUser systemUser
    ) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<SystemUser> criteriaUpdate = cb
                    .createCriteriaUpdate(SystemUser.class);
            Root<SystemUser> root = criteriaUpdate.from(SystemUser.class);
            criteriaUpdate.set("firstName", systemUser.getFirstName());
            criteriaUpdate.set("lastName", systemUser.getLastName());
            criteriaUpdate.set("birthday", systemUser.getBirthday());
            criteriaUpdate.set("address", systemUser.getAddress());
            criteriaUpdate.set("passportNumber", systemUser.getPassportNumber());
            criteriaUpdate.set("phoneNumber", systemUser.getPhoneNumber());
            criteriaUpdate.set("userRole", systemUser.getUserRole());
            criteriaUpdate.set("userLogin", systemUser.getUserLogin());
            criteriaUpdate.where(cb.equal(root.get("id"), systemUser.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
