package com.springboot.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //  Lets you automatically produce the code required to have your class be instantiable with code
public class Book {

    private String isbn;
    private String title;
    private Long authorId; // We don't use long because it defaults to 0. Long defaults to null.

}
