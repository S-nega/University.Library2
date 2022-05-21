package kz.narxoz.demo.service;

import kz.narxoz.demo.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    Book saveBook(Book book);

    void deleteBook(Long id);

    Book editBook(Book book, Long id);

    Book findOneById(Long id);
}
