package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(40) NOT NULL," +
                "lastName VARCHAR(40) NOT NULL," +
                "age TINYINT NOT NULL)";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createSQLQuery(sql);
                query.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createSQLQuery(sql);
                query.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(new User(name, lastName, age));
                transaction.commit();
                System.out.println("User с именем — " + name + " добавлен в базу данных");
            } catch (HibernateException e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = session.get(User.class, id);
                session.remove(user);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        String hql = "FROM User";
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(hql, User.class);
            return query.getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createSQLQuery(sql);
                query.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                throw new RuntimeException(e);
            }
        }
    }
}
