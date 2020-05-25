package com.booksysdemo.demo.controller;

import com.booksysdemo.demo.entity.Book;
import com.booksysdemo.demo.reposity.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return bookRepository.findAll(pageable);
    }
    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id") Integer id){
        return bookRepository.findById(id).get();
    }

    @PutMapping("/update")
    public String updateBook(@RequestBody Book book){
        Book b = bookRepository.save(book);
        if(b != null){
            return "success";
        }else{
            return "false";
        }
    }
    @PostMapping("/save")
    public String save(@RequestBody Book book) {
        Example<Book> example = Example.of(book);
        Optional<Book> productOptional = bookRepository.findOne(example);

        if (productOptional.isPresent()) {
            Book u = productOptional.get();
            if(u.getBname().equals(book.getBname()) && u.getAuthor().equals(book.getAuthor())){
                System.out.println("false: " + productOptional.get().toString());
                return "false";
            } else {
                saveBook(book);
                return "success";
            }
        } else {
            saveBook(book);
            return "success";
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "success";
    }

    private void saveBook(Book book){
        Book u = bookRepository.save(book);
        if(u != null){
            System.out.println("success: " + u);
        }else{
            System.out.println("false");
        }
    }

}
