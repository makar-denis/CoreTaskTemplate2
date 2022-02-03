package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String sqlCreat = " CREATE TABLE users "
            + "(id INT(5) NOT NULL AUTO_INCREMENT, "
            + " name VARCHAR(50), "
            + "lastName VARCHAR(50), "
            + "age INT(5), "
            + "PRIMARY KEY ( id ));";
    String sqlDrop = "DROP TABLE USERS";
    String sqlClean = "TRUNCATE TABLE users";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sqlCreat).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
//            session.close();
        } catch (Exception e){}
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(sqlDrop).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
//        session.close();
    } catch (Exception e){}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession() ) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
//        query.executeUpdate();
            transaction.commit();
//            session.close();
        } catch (Exception e){}
    }

    @Override
    public void removeUserById(long id) {
        try ( Session session = Util.getSessionFactory().openSession() ) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM users WHERE id = " + id + ";");
            query.executeUpdate();
            transaction.commit();
//            session.close();
        } catch (Exception e){}
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (ArrayList<User>)  Util.getSessionFactory().openSession().createQuery("From User").list();
        return users;
//        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession() ) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sqlClean);
            query.executeUpdate();
            transaction.commit();
//        session.close();
        }catch (Exception e){}
    }
}
