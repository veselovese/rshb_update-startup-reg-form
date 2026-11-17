package ru.rshb.intech.digital.service.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.rshb.intech.digital.commons.utils.Persistence;
import ru.rshb.intech.digital.service.config.options.DatabaseOptions.DigitalDatabaseOptions;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackages = {
        "ru.rshb.intech.digital.publications.core.repository",
        "ru.rshb.intech.digital.subscriptions.core.repository",
        "ru.rshb.intech.digital.puzzles.core.crossword.repository",
        "ru.rshb.intech.digital.vacancies.core.repository",
        "ru.rshb.intech.digital.notifications.internal.repository",
        "ru.rshb.intech.digital.references.cities.core.repository",
        "ru.rshb.intech.digital.references.universities.core.repository",
        "ru.rshb.intech.digital.statistics.agrobit.salary.repository",
        "ru.rshb.intech.digital.statistics.agrobit.tourism.repository",
        "ru.rshb.intech.digital.feature.blogs.core.experts.repository",
        "ru.rshb.intech.digital.feature.statistics.agrobit.university.repository",
        "ru.rshb.intech.digital.feature.agrobit.core.repository",
        "ru.rshb.intech.digital.feature.contests.chef.repository",
        "ru.rshb.intech.digital.feature.contests.patent.repository",
        "ru.rshb.intech.digital.feature.contests.agrotech.olympiad.question.repository",
        "ru.rshb.intech.digital.feature.contests.agrotech.olympiad.registration.repository",
        "ru.rshb.intech.digital.feature.references.locations.cache.repository",
        "ru.rshb.intech.digital.feature.startups.core.repository",
        "ru.rshb.intech.digital.feature.patents.core.repository",
        "ru.rshb.intech.digital.feature.downloads.core.repository",
        "ru.rshb.intech.digital.feature.innovations.core.repository",
        "ru.rshb.intech.digital.feature.internship.core.repository",
        "ru.rshb.intech.digital.feedback.core.repository",
        "ru.rshb.intech.digital.feature.statistics.forms.core.repository",
        "ru.rshb.intech.digital.subsystem.audit.repository",
        "ru.rshb.intech.digital.subsystem.audit.notifications.repository",
        "ru.rshb.intech.digital.subsystem.content.files.repository",
        "ru.rshb.intech.digital.subsystem.content.index.repository",
        "ru.rshb.intech.digital.subsystem.contests.stipend.repository",
        "ru.rshb.intech.digital.censor.dictionary.repository",
        "ru.rshb.intech.digital.notifications.core.template.repository"

    }
)
@EntityScan(
    basePackages = {
        "ru.rshb.intech.digital.lookups.countries.entity",
        "ru.rshb.intech.digital.feedback.core.entity",
        "ru.rshb.intech.digital.subscriptions.entity",
        "ru.rshb.intech.digital.puzzles.entity",
        "ru.rshb.intech.digital.events.entity",
        "ru.rshb.intech.digital.publications.entity",
        "ru.rshb.intech.digital.vacancies.entity",
        "ru.rshb.intech.digital.notifications.entity",
        "ru.rshb.intech.digital.references.cities.entity",
        "ru.rshb.intech.digital.references.universities.entity",
        "ru.rshb.intech.digital.statistics.agrobit.salary.entity",
        "ru.rshb.intech.digital.subsystem.audit.entity",
        "ru.rshb.intech.digital.subsystem.audit.notifications.entity",
        "ru.rshb.intech.digital.subsystem.contests.stipend.entity",
        "ru.rshb.intech.digital.statistics.agrobit.tourism.entity",
        "ru.rshb.intech.digital.censor.dictionary.entity",
        "ru.rshb.intech.digital.subsystem.content.index.entity",
        "ru.rshb.intech.digital.feature.blogs.core.experts.entity",
        "ru.rshb.intech.digital.feature.statistics.agrobit.university.entity",
        "ru.rshb.intech.digital.feature.agrobit.core.entity",
        "ru.rshb.intech.digital.subsystem.content.files.entity",
        "ru.rshb.intech.digital.feature.contests.agrotech.olympiad.entity",
        "ru.rshb.intech.digital.feature.contests.chef.entity",
        "ru.rshb.intech.digital.feature.contests.patent.entity",
        "ru.rshb.intech.digital.feature.references.locations.cache.entity",
        "ru.rshb.intech.digital.feature.startups.entity",
        "ru.rshb.intech.digital.feature.patents.core.entity",
        "ru.rshb.intech.digital.feature.downloads.entity",
        "ru.rshb.intech.digital.feature.innovations.core.entity",
        "ru.rshb.intech.digital.feature.internship.core.entity"
    }
)
@EnableTransactionManagement
public class PersistenceConfig {

  @Bean
  public EntityManagerFactory entityManagerFactory(SessionFactory sessionFactory) {
    return sessionFactory;
  }

  @Bean
  @Primary
  public PlatformTransactionManager sessionTransactionManager(SessionFactory sessionFactory) {
    return new JpaTransactionManager(sessionFactory);
  }

  @Bean
  @Primary
  public SessionFactory sessionFactory(@Qualifier("digitalDataSource") DataSource dataSource) {
    return new LocalSessionFactoryBuilder(dataSource)
        .scanPackages("ru.rshb.intech.digital")
        .buildSessionFactory();
  }

  @Bean
  @Primary
  @Qualifier("digitalDataSource")
  public DataSource dataSource(DigitalDatabaseOptions dbOptions) {
    return DataSourceBuilder.create()
        .url(dbOptions.getUrl())
        .username(dbOptions.getUsername())
        .password(dbOptions.getPassword())
        .driverClassName(dbOptions.getDriverClass())
        .build();
  }

  @Bean
  @Qualifier(Persistence.DEFAULT_DB_JDBC_TEMPLATE_BEAN)
  public NamedParameterJdbcTemplate digitalNamedJdbcTemplate(@Qualifier("digitalDataSource") DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }
}
