package com.springboot.books.dao.impl;

import com.springboot.books.dao.BookDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {

    // We need the JDBC Template here. So, We use dependency injection here. ( From the bean created in the config class )
    // We make this final because to make this cannot be reassigned once it instantiated.
    private final JdbcTemplate jdbcTemplate;
    public BookDaoImpl (final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
