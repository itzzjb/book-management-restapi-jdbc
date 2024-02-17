package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.dao.impl.BookDaoImpl;
import com.springboot.books.v1.domain.Book;
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
public class BookDaoImplTests {

    @Mock // Before every test for dependencies like JdbcTemplate, A Mock will be created.
    private JdbcTemplate jdbcTemplate;
    @InjectMocks  // Before every test a new instance of BookDaoImpl will be created and the  Mocks will be injected here.
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {

        // Instantiates the object using builder().build() of @Builder
        Book book = Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();

        underTest.create(book);

        // We want to verify that a certain method is called in JdbcTemplate with a very particular set of arguments.
        // Method that we want to verify is .update()
        // Because it's creating an object it uses update JDBC method.
        verify(jdbcTemplate).update(
                eq("INSERT into books (isbn, title, author_id) VALUES (?,?,?)"),
                eq("978-1-2345-6789-0"),eq("The Shadow in the Attic"),eq(1L)
        );
    }

}
