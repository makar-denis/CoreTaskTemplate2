package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:MySQL://localhost:3306/mytest?serverTimezone=UTC&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "rout";
    private static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties(); // параметры для работы  hibernate
                properties.put(Environment.DRIVER, DRIVER); //Класс драйвера JDBC
                properties.put(Environment.URL, URL); // URL-адрес JDBC
                properties.put(Environment.USER, USER); //пользователь JDBC
                properties.put(Environment.PASS, PASSWORD);//JDBC-пароль
                properties.put(Environment.DIALECT, DIALECT); // Спящий Dialect класс SQL
                properties.put(Environment.SHOW_SQL, "true"); //Включить запись сгенерированного SQL в консоль
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");//  Контекстная область видимости для SessionFactory.getCurrentSession()обработки.
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class); // передается класс который вспринимается как сущность
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                System.out.println("Соединение усановлено");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("нет соединения");
            }
        }
        return sessionFactory;
    }

    public Util() {
        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не загрузился класс драйвер, соединение не установлено");
        }
    }

    public static Connection getConnect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
