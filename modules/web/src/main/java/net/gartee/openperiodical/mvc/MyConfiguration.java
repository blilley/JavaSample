package net.gartee.openperiodical.mvc;

import net.gartee.cqrs.CommandHandler;
import net.gartee.openperiodical.core.commandhandlers.CreateNewspaperHandler;
import net.gartee.openperiodical.core.commands.CreateNewspaper;
import net.gartee.openperiodical.data.mysql.HibernateUtil;
import net.gartee.openperiodical.data.mysql.MySqlNewspaperRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public CommandHandler<CreateNewspaper> getCreateNewspaperHandler() {
        return new CreateNewspaperHandler(
                new MySqlNewspaperRepository(
                        HibernateUtil.getSessionFactory().getCurrentSession()));
    }
}
