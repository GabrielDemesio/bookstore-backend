package bookstore.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bookstore.bookstore.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
