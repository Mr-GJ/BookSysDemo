package com.booksysdemo.demo.controller;

import com.booksysdemo.demo.entity.Book;
import com.booksysdemo.demo.reposity.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookHandlerTest {
    @Autowired
    private BookRepository bookRepository;
    @Test
    void save() {
        Book book = new Book();
        book.setBname("哈利波特");
        book.setAuthor("罗琳");
        Book b = bookRepository.save(book);
        System.out.println(b);
    }

    @Test
    void findById(){
        Book book = bookRepository.findById(2).get();
        System.out.println(book);
    }
}