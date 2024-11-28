package bookstore.bookstore.services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import bookstore.bookstore.models.Book;
import bookstore.bookstore.repositories.BookRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class BookService {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list(){
        return bookRepository.findAll();
    }

    public Book create(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Book book ){
        return bookRepository.findById(id)
                .map(record -> {
                    record.setTitle(book.getTitle());
                    record.setAuthor(book.getAuthor());
                    record.setText(book.getText());
                    Book updated = bookRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow(() -> new ResourceNotFoundException("Not found" + id));
    }

    public Book findById(@PathVariable Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found" + id));
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com ID: " + id));
        bookRepository.delete(book);
    }

}

