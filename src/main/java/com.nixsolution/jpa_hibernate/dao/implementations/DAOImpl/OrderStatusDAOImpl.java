package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.OrderStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class OrderStatusDAOImpl implements DAOInterface<OrderStatus> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(OrderStatus orderStatus) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(orderStatus);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<OrderStatus> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderStatus> cr = cb.createQuery(OrderStatus.class);
            Root<OrderStatus> root = cr.from(OrderStatus.class);
            cr.select(root);
            Query<OrderStatus> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public OrderStatus fetchById(int id) {
        OrderStatus orderStatus;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OrderStatus> criteriaQuery = cb
                    .createQuery(OrderStatus.class);
            Root<OrderStatus> root = criteriaQuery.from(OrderStatus.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<OrderStatus> query = session.createQuery(criteriaQuery);
            orderStatus = query.getSingleResult();
        }
        return orderStatus;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<OrderStatus> criteriaDelete = cb
                    .createCriteriaDelete(OrderStatus.class);
            Root<OrderStatus> root = criteriaDelete.from(OrderStatus.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(OrderStatus orderStatus) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<OrderStatus> criteriaUpdate = cb
                    .createCriteriaUpdate(OrderStatus.class);
            Root<OrderStatus> root = criteriaUpdate.from(OrderStatus.class);
            criteriaUpdate.set("name", orderStatus.getName());
            criteriaUpdate.where(cb.equal(root.get("id"), orderStatus.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
