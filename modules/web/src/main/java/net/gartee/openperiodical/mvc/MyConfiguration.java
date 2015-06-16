package net.gartee.openperiodical.mvc;

import net.gartee.cqrs.CommandHandler;
import net.gartee.openperiodical.core.commandhandlers.CreateNewspaperHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.core.persistence.repositories.HibernateNewspaperRepository;
import net.gartee.openperiodical.core.persistence.repositories.HibernateUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public CommandHandler<CreateNewspaper> getCreateNewspaperHandler() {
        return new CreateNewspaperHandler(
                new HibernateNewspaperRepository(
                        HibernateUtil.getSessionFactory().getCurrentSession()));
    }
}
