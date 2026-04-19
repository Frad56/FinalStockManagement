package com.example.store.Service.AuthService.AdminSettings.adminResetPasswordService.implementation;

import com.example.store.DTO.authentification.OldPasswordVerification;
import com.example.store.DTO.authentification.ResetPasswordRequest;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Exception.ValidationCodeException;
import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;
import com.example.store.Repository.AuthRepository.UserRepository;
import com.example.store.Service.AuthService.EmailService;
import com.example.store.Service.AuthService.AdminSettings.adminResetPasswordService.interfaces.AdminResetPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdminResetPasswordServiceImpl implements AdminResetPasswordService {


    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    public AdminResetPasswordServiceImpl(UserRepository userRepository ,EmailService emailService
    ,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder =passwordEncoder;
    }

    @Override
    public boolean isAdmin(Role role) {
        return role == Role.ADMIN;
    }



    @Override
    public void changePassword(ResetPasswordRequest passwordRequest) {

        User user = userRepository.findByEmail(passwordRequest.getEmail()).orElseThrow(()->
                new ElementNotFoundException(passwordRequest.getEmail()));

        String email = passwordRequest.getEmail();
        if(!isAdmin(user.getRole())){
            throw new IllegalArgumentException("User is not an admin");
        }


        String storedCode = emailService.getCode(email);

        if(storedCode == null) {
            throw new ValidationCodeException("Code expired! " );
        }
        if(!storedCode.equals(passwordRequest.getCode())) {
            throw new ValidationCodeException("Invalid code for email " + email);
        }

        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepository.save(user);

        emailService.deleteCode(passwordRequest.getEmail());
        user.setPassword(passwordRequest.getNewPassword());


    }

    @Override
    public void oldPasswordVerification(OldPasswordVerification oldPasswordVerification){


        System.out.println("Email "+oldPasswordVerification.getEmail());
            User user = userRepository.findByEmail(oldPasswordVerification.getEmail()).orElseThrow(()->
                new ElementNotFoundException(oldPasswordVerification.getEmail()));

            if(!isAdmin(user.getRole())){
                throw new IllegalArgumentException("User is not an admin");
            }

        if(!passwordEncoder.matches(oldPasswordVerification.getOldPassword(), user.getPassword())){
            throw  new IllegalArgumentException("Old password is incorrect");
        }

    }

}
