package kz.narxoz.demo.repository;

import kz.narxoz.demo.entity.TakenBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TakenBookRepository extends JpaRepository<TakenBook, Long> {
}
