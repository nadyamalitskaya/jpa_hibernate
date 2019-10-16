package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.Author;
import com.nixsolution.jpa_hibernate.entity.Book;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class BookDAOImpl implements DAOInterface<Book> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(Book book) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Book> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
            Root<Book> root = cr.from(Book.class);
            cr.select(root);
            Query<Book> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public Book fetchById(int id) {
        Book book;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = cb
                    .createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<Book> query = session.createQuery(criteriaQuery);
            book = query.getSingleResult();
        }
        return book;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Book> criteriaDelete = cb
                    .createCriteriaDelete(Book.class);
            Root<Book> root = criteriaDelete.from(Book.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(Book book) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Book> criteriaUpdate = cb
                    .createCriteriaUpdate(Book.class);
            Root<Book> root = criteriaUpdate.from(Book.class);
            criteriaUpdate.set("addressInStorage", book.getAddressInStorage());
            criteriaUpdate.set("language", book.getLanguage());
            criteriaUpdate.set("name", book.getName());
            criteriaUpdate.set("volume", book.getVolume());
            criteriaUpdate.where(cb.equal(root.get("id"), book.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
