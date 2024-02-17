package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.dao.AuthorDao;
import com.springboot.books.v1.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {

    // We need the JDBC Template here. So, We use dependency injection (Autowiring) here. ( From the bean created in the config class )
    // We make this final because to make this cannot be reassigned once it instantiated.
    private final JdbcTemplate jdbcTemplate;
    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update("INSERT into authors (id, name, age) VALUES (?,?,?)",
                author.getId(), author.getName(), author.getAge()
        );
    }

}
