package kz.narxoz.demo.config;

import kz.narxoz.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import kz.narxoz.demo.entity.Users;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;//является Bean'ом

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){//est' u IZ
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {//

        http.exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().antMatchers("/").hasAnyRole("USER","ADMIN");//.permitAll();
                    //.antMatchers("/students").hasRole("ADMIN")

                    //.antMatchers(HttpMethod.DELETE, "/students").hasRole("ADMIN")

                    //.antMatchers( "/students/delete/{id}").hasRole("ADMIN")
                    //.antMatchers( "/students/edit/{id}").hasRole("ADMIN")
                    //.antMatchers( "/students/new").hasRole("ADMIN")
                    //.antMatchers( "/books/delete/{id}").hasRole("ADMIN")
                    //.antMatchers( "/books/edit/{id}").hasRole("ADMIN")
                    //.antMatchers( "/books/new").hasRole("ADMIN")
                    //.antMatchers( "/students").hasAnyRole("USER","ADMIN")
        http.formLogin()
                .loginPage("/login").permitAll()
                .usernameParameter("user_login")
                .passwordParameter("user_password")
                .loginProcessingUrl("/auth").permitAll()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/profile");

        http.logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login");

//        http.csrf().disable();


                //.anyRequest()
                //.authenticated()
                //.and()
                //.formLogin()
                //.defaultSuccessUrl("/books");

    }
/*
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build(),
                User.builder()
                        .username("user")//(users.login)kz.narxoz.demo.entity.User.
                        .password(passwordEncoder().encode("user"))//(users.pas)
                        .roles("USER")
                        .build()
        );
    }

 */
}
