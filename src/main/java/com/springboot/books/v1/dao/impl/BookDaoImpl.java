package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.dao.BookDao;
import com.springboot.books.v1.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {

    // We need the JDBC Template here. So, We use dependency injection (Autowiring) here. ( From the bean created in the config class )
    // We make this final because to make this cannot be reassigned once it instantiated.
    private final JdbcTemplate jdbcTemplate;
    public BookDaoImpl (final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT into books (isbn, title, author_id) VALUES (?,?,?)",
                book.getIsbn(),book.getTitle(),book.getAuthorId()
        );
    }

}
