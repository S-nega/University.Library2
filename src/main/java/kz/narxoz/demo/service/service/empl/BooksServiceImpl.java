package kz.narxoz.demo.service.service.empl;

import kz.narxoz.demo.entity.Book;
import kz.narxoz.demo.repository.BookRepository;
import kz.narxoz.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BooksServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book editBook(Book book, Long id) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public Book findOneById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

}
