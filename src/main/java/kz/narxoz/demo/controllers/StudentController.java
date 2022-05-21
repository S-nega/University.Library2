package kz.narxoz.demo.controllers;

import kz.narxoz.demo.entity.Student;
import kz.narxoz.demo.entity.Users;
import kz.narxoz.demo.repository.StudentRepository;
import kz.narxoz.demo.service.StudentSevice;
import kz.narxoz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class StudentController {
    //final
    //StudentSevice studentService;

    @Autowired
    private StudentSevice studentService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;//BCryptPasswordEncoder

    public StudentController(StudentSevice studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    @PreAuthorize("isAuthenticated()")
    public String listStudents(Model model){
        model.addAttribute("students", studentService.findAllStudents());
        return "Students";
    }

    @GetMapping("/students/new")
    public String addStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "addStudent";
    }

    @GetMapping("/students/edit/{id}")//post!
    public String editStudentForm(@PathVariable(value="id") Long id, Model model){
        //Student student = replace(Student student, Student newStudent);
        model.addAttribute("student", studentService.findOneById(id));
        //studentService.editStudent(student, id);
        return "editStudent";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable(value="id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @PostMapping("/student/edit/{id}")
    public String editStudent(@PathVariable(value="id") Long id,
                                @ModelAttribute("student") Student student){
    Student existingStudent = studentService.findOneById(id);
    existingStudent.setId(id);
    existingStudent.setFirstName(student.getFirstName());
    existingStudent.setLastName(student.getLastName());
    existingStudent.setEmail(student.getEmail());
    existingStudent.setPhone(student.getPhone());
    studentService.saveStudent(existingStudent);
    return "redirect:/students";
    }

    @GetMapping("/success")
    public String success(){
        return "login";
    }




    //obschee
    @GetMapping(value = "/403")
    public String accessDenied(Model model){
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model){

        model.addAttribute("currentUser", getUserData());
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUser", getUserData());
        return "profile";
    }

    @GetMapping(value = "/register")
    public String register(Model model){

        model.addAttribute("currentUser", getUserData());//можно напихать эту сирочку во все get запросы
        return "register";
    }

    @PostMapping(value = "/register")
    public String toRegister(@RequestParam(name = "user_login") String login,
                             @RequestParam(name = "user_password") String password,
                             @RequestParam(name = "user_studentId") String studentId){

        Users newUser = new Users();
        newUser.setStudentId(studentId);
        //newUser.setPassword(password);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setLogin(login);

        if(userService.createUser(newUser)!=null){
            return "redirect:/login";
        }

        return "redirect:/register?error";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = userService.getUserByLogin(secUser.getUsername());
            return myUser;
        }
        return null;
    }

}
