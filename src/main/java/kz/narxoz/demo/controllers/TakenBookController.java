package kz.narxoz.demo.controllers;

import kz.narxoz.demo.entity.TakenBook;
import kz.narxoz.demo.repository.TakenBookRepository;
import kz.narxoz.demo.service.TakenBookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class TakenBookController {
    final
    TakenBookService takenBookService;

    public TakenBookController(TakenBookService takenBookService) {
        this.takenBookService = takenBookService;
    }

    @GetMapping("/takenBooks")
    @PreAuthorize("isAuthenticated()")
    public String listTakenBooks(Model model){
        model.addAttribute("takenBooks", takenBookService.findAllTakenBooks());
        return "takenBooks";
    }

    @GetMapping("/takenBooks/new")
    public String addTakenBookForm(Model model){
        TakenBook takenBook = new TakenBook();
        model.addAttribute("takenBook", takenBook);
        return "addTakenBook";
    }

    @GetMapping("/takenBooks/edit/{id}")//post!
    public String editTakenBookForm(@PathVariable(value="id") Long id, Model model){
        model.addAttribute("takenBook", takenBookService.findOneById(id));
        return "editTakenBook";
    }

    @GetMapping("/takenBooks/delete/{id}")
    public String deleteTakenBook(@PathVariable(value="id") Long id){
        takenBookService.deleteTakenBook(id);
        return "redirect:/takenBooks";
    }

    @PostMapping("/takenBooks")
    public String saveTakenBook(@ModelAttribute("takenBook") TakenBook takenBook){
        takenBookService.saveTakenBook(takenBook);
        return "redirect:/takenBooks";
    }

    @PostMapping("/takenBook/edit/{id}")
    public String editTakenBook(@PathVariable(value="id") Long id,
                              @ModelAttribute("takenBook") TakenBook takenBook){
        TakenBook existingTakenBook = takenBookService.findOneById(id);
        existingTakenBook.setId(id);
        existingTakenBook.setStudentId(takenBook.getStudentId());
        existingTakenBook.setBookId(takenBook.getBookId());
        existingTakenBook.setStatus(takenBook.getStatus());
        takenBookService.saveTakenBook(existingTakenBook);
        return "redirect:/takenBooks";
    }
/*
    @GetMapping("/success")
    public String success(){
        return "login";
    }
 */
}
