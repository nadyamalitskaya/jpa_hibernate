package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.ItemStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class ItemStatusDAOImpl implements DAOInterface<ItemStatus> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(ItemStatus itemStatus) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(itemStatus);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<ItemStatus> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ItemStatus> cr = cb.createQuery(ItemStatus.class);
            Root<ItemStatus> root = cr.from(ItemStatus.class);
            cr.select(root);
            Query<ItemStatus> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public ItemStatus fetchById(int id) {
        ItemStatus itemStatus;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<ItemStatus> criteriaQuery = cb
                    .createQuery(ItemStatus.class);
            Root<ItemStatus> root = criteriaQuery.from(ItemStatus.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<ItemStatus> query = session.createQuery(criteriaQuery);
            itemStatus = query.getSingleResult();
        }
        return itemStatus;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<ItemStatus> criteriaDelete = cb
                    .createCriteriaDelete(ItemStatus.class);
            Root<ItemStatus> root = criteriaDelete.from(ItemStatus.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(ItemStatus itemStatus) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<ItemStatus> criteriaUpdate = cb
                    .createCriteriaUpdate(ItemStatus.class);
            Root<ItemStatus> root = criteriaUpdate.from(ItemStatus.class);
            criteriaUpdate.set("name", itemStatus.getName());
            criteriaUpdate.where(cb.equal(root.get("id"), itemStatus.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
