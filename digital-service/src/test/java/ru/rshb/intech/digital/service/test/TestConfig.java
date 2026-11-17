package ru.rshb.intech.digital.service.test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public JavaMailSender javaMailSenderTest() {
        return Mockito.mock(JavaMailSender.class);
    }


    @Bean
    @Primary
    public DataSource dataSourceTest() {
        final var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:25432/Digital");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("postgres");

        return new HikariDataSource(hikariConfig);
    }
}
