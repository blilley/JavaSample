package net.gartee.openperiodical.mvc.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class SpringConfig {

    @Bean
    @Autowired
    @Lazy
    public Session getSession(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        return session;
    }
}