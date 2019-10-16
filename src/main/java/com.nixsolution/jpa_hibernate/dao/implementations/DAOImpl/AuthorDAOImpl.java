package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.Author;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class AuthorDAOImpl implements DAOInterface<Author> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(Author author) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Author> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Author> cr = cb.createQuery(Author.class);
            Root<Author> root = cr.from(Author.class);
            cr.select(root);
            Query<Author> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public Author fetchById(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Author> criteriaQuery = cb.createQuery(Author.class);
            Root<Author> root = criteriaQuery.from(Author.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<Author> query = session.createQuery(criteriaQuery);
            return query.getSingleResult();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Author> criteriaDelete = cb
                    .createCriteriaDelete(Author.class);
            Root<Author> root = criteriaDelete.from(Author.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(Author author) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Author> criteriaUpdate = cb
                    .createCriteriaUpdate(Author.class);
            Root<Author> root = criteriaUpdate.from(Author.class);
            criteriaUpdate.set("firstName", author.getFirstName());
            criteriaUpdate.set("middleName", author.getMiddleName());
            criteriaUpdate.set("lastName", author.getLastName());
            criteriaUpdate.set("sex", author.getSex());
            criteriaUpdate.set("birthday", author.getBirthday());
            criteriaUpdate.where(cb.equal(root.get("id"), author.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

}
