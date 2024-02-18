package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.TestDataUtil;
import com.springboot.books.v1.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// This is an integration test
// We need to check whether the SQL runs correctly with a Database In-memory or Otherwise.
// We are going to run the test with In-memory SQL database in PostgreSQL mode.
// If we name this as AuthorDaoImplIT, when configured Maven will run this at verify step.
@SpringBootTest // Starts up a test version of our application when the test runs.
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTest {

    // We need to dependency inject a object of AuthorDaoImpl here
    // For that we are using the @Autowired annotation method instead of using config classes.
    // If you have multiple @Components/@Service of the same type we can use @Autowired with @Qualifier annotation.
    @Autowired
    private AuthorDaoImpl underTest;

     // We need to add @Autowired to the constructor that we use to inject the object of AuthorDaoImpl.
    @Autowired // Tells Spring that you should inject dependencies as declared in the constructor.
    public AuthorDaoImplIntegrationTest (AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    // To testing the creation of a Author in the database and retrieving from the databa se.
    @Test
     public void testThatAuthorCanBeCreatedAndRecalled () {

        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        // Using Optional because there is a chance we might not get a resulting object.
        Optional<Author> result = underTest.findOne(author.getId());
        // Checking whether the Optional has an object in it.
        assertThat(result).isPresent();
        // Checking whether the resulting object is equal to the inputted object.
        assertThat(result.get()).isEqualTo(author);

    }

}
