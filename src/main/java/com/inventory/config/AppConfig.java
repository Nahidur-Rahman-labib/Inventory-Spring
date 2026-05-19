package com.inventory.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.inventory")
public class AppConfig {

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds =
                new DriverManagerDataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");

        ds.setUrl(
                "jdbc:mysql://localhost:3306/inventory_spring_db"
        );

        ds.setUsername("root");

        ds.setPassword("test12345");

        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        LocalSessionFactoryBean factory =
                new LocalSessionFactoryBean();

        factory.setDataSource(dataSource());

        factory.setPackagesToScan(
                "com.inventory.entity"
        );

        Properties props = new Properties();

        props.put(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect"
        );

        props.put(
                "hibernate.show_sql",
                "true"
        );

        props.put(
                "hibernate.hbm2ddl.auto",
                "update"
        );

        factory.setHibernateProperties(props);

        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {

        return new HibernateTransactionManager(
                sessionFactory
        );
    }
}