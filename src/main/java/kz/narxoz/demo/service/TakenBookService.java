package kz.narxoz.demo.service;

import kz.narxoz.demo.entity.TakenBook;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TakenBookService {
    List<TakenBook> findAllTakenBooks();
    TakenBook saveTakenBook(TakenBook takenBook);

    void deleteTakenBook(Long id);

    TakenBook editTakenBook(TakenBook takenBook, Long id);

    TakenBook findOneById(Long id);
}
