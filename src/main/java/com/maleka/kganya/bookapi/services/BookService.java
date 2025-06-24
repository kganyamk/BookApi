package com.maleka.kganya.bookapi.services;

import com.maleka.kganya.bookapi.entities.Book;
import com.maleka.kganya.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBook(Long id) {
        return bookRepo.findById(id).orElseThrow();
    }

    public Book create(Book book) {
        // Use builder to construct the new book with available = true
        Book newBook = Book.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishedYear(book.getPublishedYear())
                .available(true)
                .build();
        return bookRepo.save(newBook);
    }

    public Book update(Long id, Book book) {
        Book existing = getBook(id);
        // Create updated book with same ID but new values
        Book updatedBook = Book.builder()
                .id(existing.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishedYear(book.getPublishedYear())
                .available(existing.isAvailable()) // preserve availability
                .build();
        return bookRepo.save(updatedBook);
    }

    public void delete(Long id) {
        bookRepo.deleteById(id);
    }

    public Book borrowBook(Long id) {
        Book book = getBook(id);
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book not available");
        }

        Book borrowed = Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishedYear(book.getPublishedYear())
                .available(false)
                .build();
        return bookRepo.save(borrowed);
    }

    public Book returnBook(Long id) {
        Book book = getBook(id);

        Book returned = Book.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publishedYear(book.getPublishedYear())
                .available(true)
                .build();
        return bookRepo.save(returned);
    }
}
