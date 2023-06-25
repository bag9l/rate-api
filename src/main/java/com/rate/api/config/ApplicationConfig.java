package com.rate.api.config;

import com.rate.api.exception.EntityNotExistsException;
import com.rate.api.model.Admin;
import com.rate.api.model.Lecturer;
import com.rate.api.model.Student;
import com.rate.api.model.User;
import com.rate.api.repository.AdminRepository;
import com.rate.api.repository.LecturerRepository;
import com.rate.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
public class ApplicationConfig {

    private final LecturerRepository lecturerRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;

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

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = findUserByLogin(username);
            System.out.println("USER:");
            System.out.println(user);
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getAuthorities());
        };
    }

//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return (request, response, authentication) -> {
//            response.sendRedirect("loginSuccess");
//        };
//    }

    private User findUserByLogin(String login) {
        Optional<Student> student = studentRepository.findStudentByLogin(login);
        Optional<Lecturer> lecturer = lecturerRepository.findLecturerByLogin(login);
        Optional<Admin> admin = adminRepository.findAdminByLogin(login);

        if (student.isPresent()) {
            return student.get();
        } else if (lecturer.isPresent()) {
            return lecturer.get();
        } else if (admin.isPresent()) {
            return admin.get();
        } else throw new EntityNotExistsException("User with username: " + login + " not found");
    }
}
