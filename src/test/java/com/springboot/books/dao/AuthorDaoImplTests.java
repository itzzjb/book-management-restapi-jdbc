package com.springboot.books.dao;

// We are setting this up as a unit test.

import com.springboot.books.dao.impl.AuthorDaoImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

// We need mokito for unit testing
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock // Before every test for dependencies like JdbcTemplate, A Mock will be created and injected into AuthorDaoImpl class.
    private JdbcTemplate jdbcTemplate;
    @InjectMocks // Before every test a new instance of AuthorDaoImpl will be created.
    private AuthorDaoImpl underTest;

}
