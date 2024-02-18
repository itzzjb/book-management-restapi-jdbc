package com.springboot.books.v1.dao.impl;

import com.springboot.books.v1.dao.BookDao;
import com.springboot.books.v1.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// We need to put the @Component annotation here because we need to tell spring to create a bean for us.
@Component
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

    @Override
    public Optional<Book> findOne(String isbn) {
        // We get the resulting objects into a list.
        List<Book> results =  jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );

        // To find the first optional and return we can use the stream().findFirst()
        return results.stream().findFirst();
    }

    // This is a nested class for the RowMapper.
    // RowMapper creates objects for results we get from the Database.
    public static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}
