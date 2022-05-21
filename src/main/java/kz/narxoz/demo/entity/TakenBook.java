package kz.narxoz.demo.entity;

import kz.narxoz.demo.repository.TakenBookRepository;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "takenBooks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TakenBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "student_id", nullable = false)
    private String StudentId;

    @Column(name = "book_id", nullable = false)
    private String BookId;

    @Column(name = "status", nullable = false)//nado li??
    private String status;
/*
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @Column(name = "count", nullable = false)//nado li??
    private String count;
 */
}
