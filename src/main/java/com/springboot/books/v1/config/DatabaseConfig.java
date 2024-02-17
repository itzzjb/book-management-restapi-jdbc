package com.springboot.books.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
// To tell SpringBoot this is a configuration class and tell to run this first thing when running the program.

public class DatabaseConfig {

    // Creating a Bean of JdbcTemplate class, which is a class given from Spring Framework.
    @Bean
    public JdbcTemplate jdbcTemplate (final DataSource dataSource) {
         return new JdbcTemplate(dataSource);
    }
}
