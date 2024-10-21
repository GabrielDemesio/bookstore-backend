package bookstore.bookstore.services;


import java.util.List;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import bookstore.bookstore.models.Book;
import bookstore.bookstore.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> list(){
        return bookRepository.findAll();
    }

    public ResponseEntity findById(@PathVariable Long id){
        return bookRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Book book ){
        return bookRepository.findById(id)
                .map(record -> {
                    record.setTitle(book.getTitle());
                    record.setAuthor(book.getAuthor());
                    record.setText(book.getText());
                    Book updated = bookRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
    public void create(@RequestBody Book book){
        bookRepository.save(book);
    }
    public ResponseEntity <?> delete(@PathVariable long id){
        return bookRepository.findById(id)
                .map(record -> {
                    bookRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

