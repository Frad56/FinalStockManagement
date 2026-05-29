package com.example.store.service.AuthService.settings.resetPasswordService.implementation;

import com.example.store.dto.authentification.EmailRequestDTO;
import com.example.store.dto.authentification.OldPasswordVerification;
import com.example.store.dto.authentification.ResetPasswordRequest;
import com.example.store.exception.ElementNotFoundException;
import com.example.store.exception.ValidationCodeException;
import com.example.store.model.authentification.Role;
import com.example.store.model.authentification.User;
import com.example.store.repository.authRepository.UserRepository;
import com.example.store.service.AuthService.EmailService;
import com.example.store.service.AuthService.settings.resetPasswordService.interfaces.ResetPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {


    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;


    public ResetPasswordServiceImpl(UserRepository userRepository , EmailService emailService
    , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder =passwordEncoder;
    }

    @Override
    public boolean isAdmin(Role role) {
        return role == Role.ADMIN;
    }


    @Override
    public boolean existsByEmail(EmailRequestDTO dto) {
        return userRepository.existsByEmail(dto.getEmail());
    }

    @Override
    public boolean existsByEmailAndNotAdmin(EmailRequestDTO dto) {

//        boolean isAdmin = userRepository.existsByEmailAndRole(dto.getEmail(), Role.ADMIN);
//
//        if (isAdmin) {
//            throw new RuntimeException("Cet email appartient à un administrateur !");
//        }
        if(!userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("this email doesn't exist");
        }

        return userRepository.existsByEmail(dto.getEmail());
    }



    @Override
    public void changePassword(ResetPasswordRequest passwordRequest) {

        User user = userRepository.findByEmail(passwordRequest.getEmail()).orElseThrow(()->
                new ElementNotFoundException(passwordRequest.getEmail()));

        String email = passwordRequest.getEmail();



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

//            if(!isAdmin(user.getRole())){
//                throw new IllegalArgumentException("User is not an admin");
//            }

        if(!passwordEncoder.matches(oldPasswordVerification.getOldPassword(), user.getPassword())){
            throw  new IllegalArgumentException("Old password is incorrect");
        }

    }

}
