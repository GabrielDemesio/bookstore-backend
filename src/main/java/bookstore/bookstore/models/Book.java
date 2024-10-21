package bookstore.bookstore.models;


import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Validated
@Entity
@Data
@Table(name = "book")
@Getter
@Setter 
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank(message="Titulo deve ser definido")
    @Column(nullable = false, name = "title")
    private String title;

    @NotNull
    @NotBlank(message ="Nome do autor deve ser definido")
    @Column(nullable = false, name = "author")
    private String author;

    @NotNull
    @NotBlank(message = "Texto tem que ser definido")
    @Column(nullable = false, name = "text")
    private String text;
    
}
