package com.rate.api.config;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.User;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            System.out.println("////////////////////////////////////////////////////////");
//            System.out.println("////////////////////////////////////////////////////////");
//            System.out.println("1"+username+"1");
//            System.out.println("////////////////////////////////////////////////////////");
//
//            User user = studentRepository.findStudentByLogin(username).orElse(null);
//            if (user == null) {
//                user = lecturerRepository.findLecturerByLogin(username)
//                        .orElseThrow(() ->
//                                new EntityNotExistsException("User with login:" + username + " not found"));
//
//            }
//            return user;
//        };

//        return (login -> {
//            User user = studentRepository.findStudentByLogin(login).orElse(null);
//            if (user == null) {
//                user = lecturerRepository.findLecturerByLogin(login)
//                        .orElseThrow(() ->
//                                new EntityNotExistsException("User with username:" + login + " not found"));
//
//            }
//            return user;
//        });
//        return (login -> {
//            User user = studentRepository.findStudentByLogin(login).orElse(null);
//            if (user == null) {
//                user = lecturerRepository.findLecturerByLogin(login)
//                        .orElseThrow(() ->
//                                new EntityNotExistsException("User with login" + login + " not found"));
//
//            }
//            return user;
//        });
//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
////        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return new ProviderManager(authenticationProvider);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            System.out.println("********************************************");
            System.out.println("1" + username + "1");
            System.out.println("********************************************");

            User user = studentRepository.findStudentByLogin(username).orElse(null);
            System.out.println("-----------------------------------------");
            System.out.println(user);
            System.out.println("-----------------------------------------");

            if (user == null) {
                user = lecturerRepository.findLecturerByLogin(username)
                        .orElseThrow(() ->
                                new EntityNotExistsException("User with login:" + username + " not found"));

            }
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getAuthorities());
        };
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (request, response, authentication) -> {
            response.sendRedirect("loginSuccess");
        };
    }
}
