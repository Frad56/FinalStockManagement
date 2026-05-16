package com.example.store.Security.details;

import com.example.store.Repository.AuthRepository.UserRepository;
import com.example.store.Security.jwt.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.store.Model.Authentification.User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not found with username: " + username)
                );

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.isEmailChanged()
        );
    }




}
