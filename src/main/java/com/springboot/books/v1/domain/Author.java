package com.springboot.books.v1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //  Lets you automatically produce the code required to have your class be instantiable with code
public class Author {

    private Long id; // We don't use long because it defaults to 0. Long defaults to null.
    private String name;
    private Integer age; // We don't use int because it defaults to 0. Integer defaults to null.

}
