package com.springboot.books.v1;

import com.springboot.books.v1.domain.Author;
import com.springboot.books.v1.domain.Book;

// Utility classes are normally set to final
public final class TestDataUtil {

    // Set the default constructor to private because we don't need to create objects from this class.
    private TestDataUtil() {
    }

    // Instantiates the object using Author.builder().build() of @Builder
    // Extracted the test object creation into a method { .createTestAuthor() }, So we can reuse the functionality.
    // And moved that into a TestDataUtil class.
    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L) // L is to notice this is a long
                .name("Januda Bethmin")
                .age(23)
                .build();
    }

    // Instantiates the object using Book.builder().build() of @Builder
    // Extracted the test object creation into a method { .createBookAuthor() }, So we can reuse the functionality.
    // And moved that into a TestDataUtil class.
    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }
}
