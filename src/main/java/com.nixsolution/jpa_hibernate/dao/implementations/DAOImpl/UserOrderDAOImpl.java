package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.UserOrder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class UserOrderDAOImpl implements DAOInterface<UserOrder> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(UserOrder userOrder) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(userOrder);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<UserOrder> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserOrder> cr = cb.createQuery(UserOrder.class);
            Root<UserOrder> root = cr.from(UserOrder.class);
            cr.select(root);
            Query<UserOrder> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public UserOrder fetchById(int id) {
        UserOrder userOrder;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UserOrder> criteriaQuery = cb
                    .createQuery(UserOrder.class);
            Root<UserOrder> root = criteriaQuery.from(UserOrder.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<UserOrder> query = session.createQuery(criteriaQuery);
            userOrder = query.getSingleResult();
        }
        return userOrder;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<UserOrder> criteriaDelete = cb
                    .createCriteriaDelete(UserOrder.class);
            Root<UserOrder> root = criteriaDelete.from(UserOrder.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(UserOrder userOrder) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<UserOrder> criteriaUpdate = cb
                    .createCriteriaUpdate(UserOrder.class);
            Root<UserOrder> root = criteriaUpdate.from(UserOrder.class);
            criteriaUpdate.set("address", userOrder.getAddress());
            criteriaUpdate.set("orderDate", userOrder.getOrderDate());
            criteriaUpdate.set("returnDate", userOrder.getReturnDate());
            criteriaUpdate.set("item", userOrder.getItem());
            criteriaUpdate.set("systemUser", userOrder.getSystemUser());
            criteriaUpdate.set("orderStatus", userOrder.getOrderStatus());
            criteriaUpdate.where(cb.equal(root.get("id"), userOrder.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
