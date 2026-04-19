package com.example.store.config;


import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;
import com.example.store.Repository.AuthRepository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {
    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository,
                                PasswordEncoder passwordEncoder){
        return args ->{
            if(userRepository.findByUsername("admin").isEmpty()){
                User admin = new User();
                admin.setEmail("admin@stockmanagement.com");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("Admin account created");
            }
        };
    }
}
