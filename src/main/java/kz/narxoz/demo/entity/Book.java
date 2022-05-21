package kz.narxoz.demo.entity;

import kz.narxoz.demo.repository.BookRepository;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "name", nullable = false)
    private String Name;

    @Column(name = "author", nullable = false)
    private String Author;

    @Column(name = "count", nullable = false)
    private String count;
}
