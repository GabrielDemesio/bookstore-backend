package bookstore.bookstore.controllers;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import bookstore.bookstore.models.Book;
import bookstore.bookstore.services.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/books")
@Configuration
public class BookControllers{
    @Autowired
    BookService bookService;

    @GetMapping()
    public List <Book> list() {
        return bookService.list();
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity findById(@Valid @PathVariable @NotNull(message = "Id não pode ser nulo") @Min(value = 0L, message = "Id inválido") Long id) {
        return bookService.findById(id);
    }

    @ResponseBody
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody Book book){
        bookService.create(book);
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Book book ){
        return bookService.update(id, book);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return bookService.delete(id);
    }
    

}
