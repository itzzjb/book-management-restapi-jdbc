package com.springboot.books;

import com.springboot.books.domain.Author;
import com.springboot.books.domain.Book;

// Utility classes are normally set to final
public final class TestDataUtil {

    // Set the default constructor to private because we don't need to create objects from this class.
    private TestDataUtil() {
    }

    // Instantiates the object using Author.builder().build() of @Builder
    // Extracted the test object creation into a method { .createTestAuthor() }, So we can reuse the functionality.
    // And moved that into a TestDataUtil class.
    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L) // L is to notice this is a long
                .name("Januda Bethmin")
                .age(23)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L) // L is to notice this is a long
                .name("Michel Phelps")
                .age(43)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L) // L is to notice this is a long
                .name("Christoper Nolan")
                .age(50)
                .build();
    }

    // Instantiates the object using Book.builder().build() of @Builder
    // Extracted the test object creation into a method { .createBookAuthor() }, So we can reuse the functionality.
    // And moved that into a TestDataUtil class.
    public static Book createTestBookA() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("979-1-2345-6789-0")
                .title("The Blood on the Floor")
                .authorId(2L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("980-1-2345-6789-0")
                .title("The Haunting at the hill house")
                .authorId(3L)
                .build();
    }
}
