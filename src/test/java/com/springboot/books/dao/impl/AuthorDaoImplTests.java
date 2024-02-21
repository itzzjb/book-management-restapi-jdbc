package com.springboot.books.dao.impl;

import com.springboot.books.TestDataUtil;
import com.springboot.books.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

// We are setting this up as a unit test.
// This unit test are only for checking whether our SQL queries are correct.
// We need mockito for unit testing
@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTests {

    @Mock // Before every test for dependencies like JdbcTemplate, A Mock will be created.
    private JdbcTemplate jdbcTemplate;
    @InjectMocks // Before every test a new instance of AuthorDaoImpl will be created and the  Mocks will be injected here.
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {

        // We get the resulting objects into a list.
        // Extracted the test object creation into a method { .createTestAuthor() }, So we can reuse the functionality.
        // And moved that into a TestDataUtil class.
        Author author = TestDataUtil.createTestAuthorA();

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

    // There are multiple types of read methods.
    // 1. ReadOne -> Pass in an ID, If the entity exists in the DB it returns if not you may get a null.
    // 2. ReadMany -> Either ask to get all of the entities or a query to get a subset of them. You get a list of those entities.
    @Test
    public void testThatFindOneGeneratesCorrectSql() {

        underTest.findOne(1L);

        // We need to do the Mapping ( Converting the result set into an object ) manually.
        // RowMapper is one of the easy methods of doing it.
        verify(jdbcTemplate).query(
                eq("SELECT id, name , age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );

    }

    @Test
    public void testThatFindManyGeneratesCorrectSql() {

        underTest.find();

        // We need to do the Mapping ( Converting the result set into an object ) manually.
        // RowMapper is one of the easy methods of doing it.
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );

    }

    // There are two types of update methods
    // 1. Full update
    // 2. Partial update ( This will be done under hibernate of JPA )

    @Test
    public void testThatUpdateAuthorGeneratesCorrectSql() {

        // To update we require an Author object here.
        Author author = TestDataUtil.createTestAuthorA();

        underTest.update(author,3L);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(1L), eq("Januda Bethmin"), eq(23),eq(3L)
        );

    }

    @Test
    public void testThatDeleteGeneratesCorrectSql() {
        underTest.delete(1L);

        // Deletion is considered as an update to the jdbc template
        verify(jdbcTemplate).update(
                eq("DELETE FROM authors WHERE id = ?"),
                eq(1L)
        );

    }

}
