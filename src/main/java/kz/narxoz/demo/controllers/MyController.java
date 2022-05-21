package kz.narxoz.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyController {

    @GetMapping("/")
    public String Hello() { return "Hello world!";}

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(name="title", required = false, defaultValue = "Default Title") String title,
                       @RequestParam(name="name", required = false, defaultValue = "Default Name") String name,
                       @RequestParam(name="surname", required = false, defaultValue = "Default Surname") String surname, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "main";
    }
}
