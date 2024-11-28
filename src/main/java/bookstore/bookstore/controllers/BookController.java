package bookstore.bookstore.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import bookstore.bookstore.models.Book;
import bookstore.bookstore.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Listar todos os livros
    @GetMapping
    public ResponseEntity<List<Book>> list() {
        List<Book> books = bookService.list();
        return ResponseEntity.ok(books);
    }

    // Buscar livro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(
            @PathVariable @NotNull(message = "Id não pode ser nulo")
            @Min(value = 1, message = "Id deve ser maior que 0") Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    // Criar um novo livro
    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        Book createdBook = bookService.create(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    // Atualizar um livro existente
    @PutMapping("/{id}")
    public ResponseEntity<ResponseEntity> update(
            @PathVariable @NotNull(message = "Id não pode ser nulo")
            @Min(value = 1, message = "Id deve ser maior que 0") Long id,
            @Valid @RequestBody Book book) {
        ResponseEntity updatedBook = bookService.update(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    // Deletar um livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable @NotNull(message = "Id não pode ser nulo")
            @Min(value = 1, message = "Id deve ser maior que 0") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
