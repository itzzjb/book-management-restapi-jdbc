package com.springboot.books.dao.impl;

import com.springboot.books.dao.AuthorDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {

    // We need the JDBC Template here. So, We use dependency injection here. ( From the bean created in the config class )
    // We make this final because to make this cannot be reassigned once it instantiated.
    private final JdbcTemplate jdbcTemplate;
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
