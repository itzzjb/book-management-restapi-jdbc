package com.springboot.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private String isbn;
    private String title;
    private Long authorId; // We don't use long because it defaults to 0. Long defaults to null.

}
