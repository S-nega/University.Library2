package kz.narxoz.demo.service;

import kz.narxoz.demo.entity.Users;
import kz.narxoz.demo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;//

    //List<Users> findAllUsers();
    //Users saveUser(Users user);//users???

    //void deleteUser(Long id);

    //Users editUser(Users user, Long id);

    //Users findOneById(Long id);

    Users getUserByLogin(String login);

    Users createUser(Users user);

}
