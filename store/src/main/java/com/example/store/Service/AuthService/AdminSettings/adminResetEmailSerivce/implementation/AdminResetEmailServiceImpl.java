package com.example.store.Service.AuthService.AdminSettings.adminResetEmailSerivce.implementation;


import com.example.store.DTO.authentification.UserResponse;
import com.example.store.DTO.authentification.resetEmail.ResetEmailRequest;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Exception.UnauthorizedException;
import com.example.store.Exception.ValidationCodeException;
import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;
import com.example.store.Repository.AuthRepository.UserRepository;
import com.example.store.Service.AuthService.EmailService;
import com.example.store.Service.AuthService.AdminSettings.adminResetEmailSerivce.interfaces.AdminResetEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminResetEmailServiceImpl implements AdminResetEmailService {


    private final UserRepository userRepository;
    private  final EmailService emailService;

    @Autowired
    public AdminResetEmailServiceImpl(UserRepository userRepository,
                                      EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public boolean isAdmin(Role role) {
        return role == Role.ADMIN;
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()
                -> new ElementNotFoundException( " with email " + email + " not found"));
    }

    @Override
    public String findEmailByName(String name) {
        return userRepository.findEmailByName(name).orElseThrow(()
                -> new ElementNotFoundException("Email for user with name " + name + " not found"));
    }

    @Override
    public UserResponse resetEmail(ResetEmailRequest request) {

        User user =findUserByEmail(request.getOldEmail());

        if(!isAdmin(user.getRole())){
            throw new UnauthorizedException("User with email " + request.getOldEmail() + " is not an admin :"+user.getRole());
        }
//        if(user.isEmailChanged()){
//            throw new UnauthorizedException("User with email " + request.getOldEmail() + " has already changed their email once, cannot change again");
//
//        }

        String storedCode = emailService.getCode(request.getNewEmail());

        if(storedCode == null) {
            throw new ValidationCodeException("Code expired " + request.getNewEmail());
        }
        if(!storedCode.equals(request.getCode())) {
            throw new ValidationCodeException("Invalid code for email " + request.getNewEmail());
        }

        user.setEmail(request.getNewEmail());
        user.setEmailChanged(true);

        userRepository.save(user);
        emailService.deleteCode(request.getNewEmail());
        return  new UserResponse(user.getUsername(), user.getEmail());
    }

//    @Override
//    public UserResponse resetEmailAndResetCode(ResetEmailRequest request) {
//        emailService.deleteCode(request.getNewEmail());
//        resetEmail(request);
//        return new UserResponse(request.getOldEmail(), request.getNewEmail());
//
//    }


}