package com.springboot.books.v1.dao;

import com.springboot.books.v1.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    // Optional is a container either it has something init or don't.
    // If we try to return an object from the database if the object is not in the database  it returns null.
    // After we retrieve that object we normally use methods on it.
    // But we can't call methods on when the event of not finding the object. This results a null pointer exception.
    // For this situation we use Optionals.
    // Optional is like a box. We we find the object in the database the object will be put into the box and returned.
    // Otherwise, only the box will be returned.
    Optional<Book> findOne(String isbn);

    List<Book> find();

    void update(Book book, String isbn);
}
