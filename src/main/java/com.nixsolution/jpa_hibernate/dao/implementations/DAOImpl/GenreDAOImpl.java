package com.nixsolution.jpa_hibernate.dao.implementations.DAOImpl;

import com.nixsolution.jpa_hibernate.HibernateUtil;
import com.nixsolution.jpa_hibernate.dao.interfaces.DAOInterface;
import com.nixsolution.jpa_hibernate.entity.Genre;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class GenreDAOImpl implements DAOInterface<Genre> {
    private static final Logger logger = LogManager
            .getLogger(AuthorDAOImpl.class);

    @Override
    public void add(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            session.beginTransaction();
            session.save(genre);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public List<Genre> fetchAll() {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Genre> cr = cb.createQuery(Genre.class);
            Root<Genre> root = cr.from(Genre.class);
            cr.select(root);
            Query<Genre> query = session.createQuery(cr);
            return query.getResultList();
        }
    }

    @Override
    public Genre fetchById(int id) {
        Genre genre;
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Genre> criteriaQuery = cb.createQuery(Genre.class);
            Root<Genre> root = criteriaQuery.from(Genre.class);
            criteriaQuery.where(cb.equal(root.get("id"), id));
            Query<Genre> query = session.createQuery(criteriaQuery);
            genre = query.getSingleResult();
        }
        return genre;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Genre> criteriaDelete = cb
                    .createCriteriaDelete(Genre.class);
            Root<Genre> root = criteriaDelete.from(Genre.class);
            criteriaDelete.where(cb.equal(root.get("id"), id));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaDelete).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }

    @Override
    public void update(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaUpdate<Genre> criteriaUpdate = cb
                    .createCriteriaUpdate(Genre.class);
            Root<Genre> root = criteriaUpdate.from(Genre.class);
            criteriaUpdate.set("description", genre.getDescription());
            criteriaUpdate.set("name", genre.getName());
            criteriaUpdate.where(cb.equal(root.get("id"), genre.getId()));
            Transaction transaction = session.beginTransaction();
            session.createQuery(criteriaUpdate).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.error(e);
        }
    }
}
