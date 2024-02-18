package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.TestDataUtil;
import com.springboot.books.v1.domain.Author;
import com.springboot.books.v1.domain.Book;
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
public class BookDaoImplIntegrationTest {

    // We need to dependency inject a object of BookDaoImpl here
    // For that we are using the @Autowired annotation method instead of using config classes.
    // If you have multiple @Components/@Service of the same type we can use @Autowired with @Qualifier annotation.
    @Autowired
    private BookDaoImpl underTest;
    // For creating an author object for the foreign key constraint.
    @Autowired
    private AuthorDaoImpl authorDao;

    // We need to add @Autowired to the constructor that we use to inject the objects of BookDaoImpl and AuthorDaoImpl.
    @Autowired // Tells Spring that you should inject dependencies as declared in the constructor.
    public BookDaoImplIntegrationTest (BookDaoImpl underTest, AuthorDaoImpl authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    // To testing the creation of a Book in the database and retrieving from the database.
    @Test
    public void testThatBookCanBeCreatedAndRecalled() {

        // We need to create the book before creating the author.
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);

        // So, There is a foreign key constraint in the Book table.
        // So, there should be a author inorder to test to work.
        // So, We need to create an Author object too.
        // We only can get these from integration tests, never from Unit tests.
        Book book = TestDataUtil.createTestBook();
        // To set the match the authorId of the test objects.
        book.setAuthorId(author.getId());
        underTest.create(book);

        // Using Optional because there is a chance we might not get a resulting object.
        Optional<Book> result = underTest.findOne(book.getIsbn());
        // Checking whether the Optional has an object in it.
        assertThat(result).isPresent();
        // Checking whether the resulting object is equal to the inputted object.
        assertThat(result.get()).isEqualTo(book);

    }
}
