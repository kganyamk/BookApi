package com.maleka.kganya.bookapi.controllers;

import com.maleka.kganya.bookapi.entities.Book;
import com.maleka.kganya.bookapi.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "CRUD and borrowing operations for books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/test")
    public String test() {
        return "Working";
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public List<Book> all() {
        return service.getAllBooks();
    }

    @Operation(summary = "Get a book by ID")
    @GetMapping("/{id}")
    public Book get(
            @Parameter(description = "ID of the book") @PathVariable Long id) {
        return service.getBook(id);
    }

    @Operation(summary = "Create a new book")
    @PostMapping
    public Book create(@RequestBody Book book) {
        return service.create(book);
    }

    @Operation(summary = "Update an existing book")
    @PutMapping("/{id}")
    public Book update(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @RequestBody Book book) {
        return service.update(id, book);
    }

    @Operation(summary = "Delete the book")
    @DeleteMapping("/{id}")
    public void delete(
            @Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Borrow a book")
    @PostMapping("/{id}/borrow")
    public Book borrow(@Parameter(description = "ID of the book to borrow") @PathVariable Long id) {
        return service.borrowBook(id);
    }

    @Operation(summary = "Return a book")
    @PostMapping("/{id}/return")
    public Book returnBook(@Parameter(description = "ID of the book to return") @PathVariable Long id) {
        return service.returnBook(id);
    }
}


