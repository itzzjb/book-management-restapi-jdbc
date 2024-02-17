package com.springboot.books.dao;

// We are setting this up as a unit test.

import com.springboot.books.dao.impl.AuthorDaoImpl;
import com.springboot.books.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

// We need mockito for unit testing
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock // Before every test for dependencies like JdbcTemplate, A Mock will be created and injected into AuthorDaoImpl class.
    private JdbcTemplate jdbcTemplate;
    @InjectMocks // Before every test a new instance of AuthorDaoImpl will be created.
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {

        Author author = Author.builder()
                .id(1L) // L is to notice this is a long
                .name("Januda Bethmin")
                .age(23)
                .build();

        underTest.create(author);

        // We want to verify that a certain method is called in JdbcTemplate with a very particular set of arguments.
        // Method that we want to verify is .update()
        // Because it's creating an object it uses update JDBC method.
        verify(jdbcTemplate).update(
                // In mockito we need to use matches instead of real values.
                eq("INSERT into authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L),eq("Januda Bethmin"),eq(23)
        );

    }

}
