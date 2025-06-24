package com.maleka.kganya.bookapi.controllers;

import com.maleka.kganya.bookapi.entities.Book;
import com.maleka.kganya.bookapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> all() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return service.getBook(id);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return service.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return service.update(id, book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/borrow")
    public Book borrow(@PathVariable Long id) {
        return service.borrowBook(id);
    }

    @PostMapping("/{id}/return")
    public Book returnBook(@PathVariable Long id) {
        return service.returnBook(id);
    }
}

