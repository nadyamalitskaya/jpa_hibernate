package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.Item;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class ItemDAOImpl implements DAOInterface<Item> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(Item item) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Item> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Item> cr = cb.createQuery(Item.class);
            Root<Item> root = cr.from(Item.class);
            cr.select(root);
            Query<Item> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public Item fetchById(int id) {
        Item item;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = cb
                    .createQuery(Item.class);
            Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<Item> query = session.createQuery(criteriaQuery);
            item = query.getSingleResult();
        }
        return item;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Item> criteriaDelete = cb
                    .createCriteriaDelete(Item.class);
            Root<Item> root = criteriaDelete.from(Item.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(Item item) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Item> criteriaUpdate = cb
                    .createCriteriaUpdate(Item.class);
            Root<Item> root = criteriaUpdate.from(Item.class);
            criteriaUpdate.set("publishOffice", item.getPublishOffice());
            criteriaUpdate.set("publishYear", item.getPublishYear());
            criteriaUpdate.set("itemStatus", item.getItemStatus());
            criteriaUpdate.set("book", item.getBook());
            criteriaUpdate.where(cb.equal(root.get("id"), item.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
