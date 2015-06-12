package net.gartee.openperiodical.data.mysql;

import net.gartee.openperiodical.data.entities.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }

        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.put("hibernate.connection.url", "jdbc:mysql://192.168.56.102/periodicalmanager");
        props.put("hibernate.connection.username", "test");
        props.put("hibernate.connection.password", "mysql");
        props.put("hibernate.current_session_context_class", "thread");
        props.put("hibernate.hbm2ddl.auto", "create-drop");

        return buildSessionFactory(props);
    }

    private static SessionFactory buildSessionFactory(Properties props) {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperties(props);

            registerEntities(configuration);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(NewspaperData.class);
    }
}