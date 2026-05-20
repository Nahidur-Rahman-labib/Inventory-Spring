package com.inventory.config;

import jakarta.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.jpa.JpaTransactionManager;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableScheduling
@ComponentScan(basePackages = "com.inventory")
public class AppConfig {

    // DATABASE
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds =
                new DriverManagerDataSource();

        ds.setDriverClassName(
                "com.mysql.cj.jdbc.Driver"
        );

        ds.setUrl(
                "jdbc:mysql://localhost:3306/inventory_spring_db"
        );

        ds.setUsername("root");

        ds.setPassword("test12345");

        return ds;
    }

    // ENTITY MANAGER FACTORY
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean emf =
                new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource());

        emf.setPackagesToScan(
                "com.inventory.entity"
        );

        emf.setJpaVendorAdapter(
                new HibernateJpaVendorAdapter()
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

        emf.setJpaProperties(props);

        return emf;
    }

    // TRANSACTION MANAGER
    @Bean
    public JpaTransactionManager transactionManager( //Controls commit/rollback behavior.
            EntityManagerFactory emf
    ) {

        return new JpaTransactionManager(emf);
    }
}