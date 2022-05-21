package kz.narxoz.demo.controllers;

import kz.narxoz.demo.entity.Book;
import kz.narxoz.demo.repository.BookRepository;
import kz.narxoz.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class BookController {
    //final
    //BookService bookService;

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @PreAuthorize("isAuthenticated()")
    public String listBooks(Model model){
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @GetMapping("/books/new")
    public String addBookForm(Model model){
        Book book = new Book();

        //model.addAttribute("currentUser", getUserData());

        //List<Book> books = bookService.getAllBools();
        model.addAttribute("book", book);
        return "addBook";
    }

    @GetMapping("/books/edit/{id}")//post!
    public String editBookForm(@PathVariable(value="id") Long id, Model model){
        model.addAttribute("book", bookService.findOneById(id));
        return "editBook";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable(value="id") Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @PostMapping("/books")
    public String saveBook(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @PostMapping("/book/edit/{id}")
    public String editBook(@PathVariable(value="id") Long id,
                              @ModelAttribute("book") Book book){
        Book existingBook = bookService.findOneById(id);
        existingBook.setId(id);
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCount(book.getCount());
        bookService.saveBook(existingBook);
        return "redirect:/books";
    }

    //@GetMapping("/success")
    //public String success(){
      //  return "login";
    //}
}
