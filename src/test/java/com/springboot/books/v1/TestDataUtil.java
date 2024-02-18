package com.springboot.books.v1;

import com.springboot.books.v1.domain.Author;

// Utility classes are normally set to final
public final class TestDataUtil {

    // Set the default constructor to private because we don't need to create objects from this class.
    private TestDataUtil() {
    }

    // Extracted the test object creation into a method { .createTestAuthor() }, So we can reuse the functionality.
    // And moved that into a TestDataUtil class.
    public static Author createTestAuthor() {

        // Instantiates the object using builder().build() of @Builder
        return Author.builder()
                .id(1L) // L is to notice this is a long
                .name("Januda Bethmin")
                .age(23)
                .build();
    }


}
