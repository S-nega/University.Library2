package kz.narxoz.demo.service.service.empl;

import kz.narxoz.demo.entity.Roles;
import kz.narxoz.demo.entity.Users;
import kz.narxoz.demo.repository.RoleRepository;
import kz.narxoz.demo.service.UserService;
import kz.narxoz.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;//???
import org.springframework.stereotype.Service;

import java.util.ArrayList;//???
import java.util.List;
@Service
public class UsersServiceImpl implements UserService {//abstract

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;//BCryptPasswordEncoder

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{

        Users myUser = userRepository.findByLogin(s);
        if(myUser!=null){

            User secUser = new User(myUser.getLogin(), myUser.getPassword(), myUser.getRoles());
            return secUser;
        }

        throw new UsernameNotFoundException("User Not Found");

    }

    @Override
    public Users getUserByLogin(String login){
        return userRepository.findByLogin(login);
    }

    @Override
    public Users createUser(Users user){
        Users checkUser = userRepository.findByLogin(user.getLogin());
        if(checkUser == null){
            Roles role = roleRepository.findByRole("ROLE_USER");
            if(role!=null){
                ArrayList<Roles> roles = new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);
                user.setPassword(user.getPassword());
                return userRepository.save(user);
            }
        }
        return null;
    }


/*
    @Override
    public List<Users> findAllUsers() {
        return null;
    }

    @Override
    public Users saveUser(Users user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public Users editUser(Users user, Long id) {
        return null;
    }

    @Override
    public Users findOneById(Long id) {
        return null;
    }
*/

}
