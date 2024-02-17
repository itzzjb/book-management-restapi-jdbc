package com.springboot.books.v1.dao;

import com.springboot.books.v1.dao.impl.AuthorDaoImpl;
import com.springboot.books.v1.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

// We are setting this up as a unit test.
// We need mockito for unit testing
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock // Before every test for dependencies like JdbcTemplate, A Mock will be created.
    private JdbcTemplate jdbcTemplate;
    @InjectMocks // Before every test a new instance of AuthorDaoImpl will be created and the  Mocks will be injected here.
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {

        // Instantiates the object using builder().build() of @Builder
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
                eq("INSERT into authors (id, name, age) VALUES (?,?,?)"),
                eq(1L),eq("Januda Bethmin"),eq(23)
        );

    }

}
