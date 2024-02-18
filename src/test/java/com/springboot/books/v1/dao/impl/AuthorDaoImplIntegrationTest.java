package com.springboot.books.v1.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// This is an integration test
// We need to check whether the SQL runs correctly with a Database In-memory or Otherwise.
// We are going to run the test with In-memory SQL database in PostgreSQL mode.
// If we name this as AuthorDaoImplIT, when configured Maven will run this at verify step.
@SpringBootTest // Starts up a test version of our application when the test runs.
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTest {

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled () {

    }

}
