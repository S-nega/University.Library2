package kz.narxoz.demo.controllers;

import kz.narxoz.demo.entity.Users;
import kz.narxoz.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    final
    UserService userService;

    /*@GetMapping("/registration")
    public String registration(){
        return "registration";
    }*/

    public UserController(UserService userService){
        this.userService = userService;
    }
/*
    @GetMapping("/users")
    @PreAuthorize("isAuthenticated()")
    public String listUsers(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "Users";
    }

 */
/*
    @GetMapping(value = "/users/new")//???
    public String addUserForm(@RequestParam(name = "login") String login,
                              @RequestParam(name = "password") String password,
                              @RequestParam(name = "role") String role){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);

        return "redirect:/register?success";
    }
    */

    @GetMapping("/users/new")//без этого не срабатвает добавление
    public String addUserForm(Model model){
        Users user = new Users();
        model.addAttribute("user", user);
        return "addUser";
    }
/*
    @GetMapping("/users/edit/{id}")//post!
    public String editUserForm(@PathVariable(value="id") Long id, Model model){
        model.addAttribute("user", userService.findOneById(id));
        return "editUser";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(value="id") Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") Users user){
        //if(user.getStudentId()==student.getId()){
            userService.saveUser(user);
        //}
        return "redirect:/login";
    }

    @PostMapping("/user/edit/{id}")
    public String editUser(@PathVariable(value="id") Long id,
                              @ModelAttribute("user") Users user){
        Users existingUser = userService.findOneById(id);
        existingUser.setId(id);
        existingUser.setLogin(user.getLogin());
        existingUser.setPassword(user.getPassword());
        existingUser.setStudentId(user.getStudentId());
        userService.saveUser(existingUser);
        return "redirect:/users";
    }

 */
}
