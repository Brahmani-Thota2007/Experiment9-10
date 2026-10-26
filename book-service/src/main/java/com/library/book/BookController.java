package com.library.book;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    @GetMapping
    public List<Book> all() {
        return books.values().stream().sorted(Comparator.comparing(Book::id)).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> get(@PathVariable Long id) {
        Book book = books.get(id);
        return book == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookRequest request) {
        if (request.title() == null || request.title().isBlank() ||
            request.author() == null || request.author().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        long id = sequence.incrementAndGet();
        Book book = new Book(id, request.title(), request.author());
        books.put(id, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookRequest request) {
        if (!books.containsKey(id)) return ResponseEntity.notFound().build();
        if (request.title() == null || request.title().isBlank() ||
            request.author() == null || request.author().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Book book = new Book(id, request.title(), request.author());
        books.put(id, book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return books.remove(id) == null
            ? ResponseEntity.notFound().build()
            : ResponseEntity.noContent().build();
    }
}
