package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.TestDataUtil;
import com.springboot.books.v1.domain.Author;
import com.springboot.books.v1.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// This is an integration test
// We need to check whether the SQL runs correctly with a Database In-memory or Otherwise.
// We are going to run the test with In-memory SQL database in PostgreSQL mode.
// If we name this as AuthorDaoImplIT, when configured Maven will run this at verify step.
@SpringBootTest // Starts up a test version of our application when the test runs.
@ExtendWith(SpringExtension.class)

// You can see that we have createdTestBookA multiple times in multiple test.
// This will result in errors.
// We need to have a fresh database for every single test we are running.
// For this we are using the @DirtiesContext() annotation.
// This will clean down the context including the database depending on how you tell it to (classMode).
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
    public void testThatBooksCanBeCreatedAndRecalled() {

        // We need to create the author before creating the book.
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        // So, There is a foreign key constraint in the Book table.
        // So, there should be a author inorder to test to work.
        // So, We need to create an Author object too.
        // We only can get these from integration tests, never from Unit tests.
        Book book = TestDataUtil.createTestBookA();
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

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {

        // We need to create the authors before creating books.
        Author authorA =  TestDataUtil.createTestAuthorA();
        Author authorB = TestDataUtil.createTestAuthorB();
        Author authorC =  TestDataUtil.createTestAuthorC();

        authorDao.create(authorA);
        authorDao.create(authorB);
        authorDao.create(authorC);

        // So, There is a foreign key constraint in the Book table.
        // So, there should be authors inorder to test to work.
        // So, We need to create an Author objects too.
        // We only can get these from integration tests, never from Unit tests.
        Book bookA = TestDataUtil.createTestBookA();
        Book bookB = TestDataUtil.createTestBookB();
        Book bookC = TestDataUtil.createTestBookC();

        // To set the match the authorId of the test objects.
        bookA.setAuthorId(authorA.getId());
        bookB.setAuthorId(authorB.getId());
        bookC.setAuthorId(authorC.getId());

        underTest.create(bookA);
        underTest.create(bookB);
        underTest.create(bookC);

        // Using a List to get the resulting objects from the database.
        List<Book> result = underTest.find();
        // Checking whether the resulting array has all the inputted objects.
        assertThat(result).hasSize(3);
        // Checking whether the resulting array contains exactly what objects we inputted.
        assertThat(result).contains(bookA,bookB,bookC);

    }

    @Test
    public void testThatBooksCanBeUpdated() {
        // We need to create the authors before creating books.
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        // So, There is a foreign key constraint in the Book table.
        // So, there should be a author inorder to test to work.
        // So, We need to create an Author object too.
        // We only can get these from integration tests, never from Unit tests.
        Book book = TestDataUtil.createTestBookA();
        // To set the match the authorId of the test objects.
        book.setAuthorId(author.getId());
        underTest.create(book);

        // Making changers to the above object and updating it
        book.setTitle("UPDATED");
        underTest.update(book,book.getIsbn());

        // Using Optional because there is a chance we might not get a resulting object.
        Optional<Book> result = underTest.findOne(book.getIsbn());
        // Checking whether the Optional has an object in it.
        assertThat(result).isPresent();
        // Checking whether the resulting object is equal to the inputted updated object.
        assertThat(result.get()).isEqualTo(book);

    }
}
