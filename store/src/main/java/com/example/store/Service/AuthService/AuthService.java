package com.example.store.service.AuthService;

import com.example.store.dto.Response.ApiResponse;
import com.example.store.dto.authentification.LoginRequest;
import com.example.store.dto.authentification.LoginResponse;
import com.example.store.dto.authentification.UserDTO;
import com.example.store.model.authentification.Role;
import com.example.store.model.authentification.User;
import com.example.store.security.details.CustomUserDetailsService;
import com.example.store.security.jwt.CustomUserDetails;
import com.example.store.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager
            , CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder,
                          UserService userService,
                          JwtUtil jwtUtil){
        this.authenticationManager =authenticationManager;
        this.customUserDetailsService =customUserDetailsService;
        this.passwordEncoder =passwordEncoder;
        this.userService=userService;
        this.jwtUtil=jwtUtil;
    }


    /////////////////////// SIGN IN //////////////////////
    public LoginResponse authenticateUser( LoginRequest user){
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        //User get_user = userService.findByUsername(user.getUsername());

        CustomUserDetails userDetails  = (CustomUserDetails) authentication.getPrincipal();
        String token =jwtUtil.generateToken(userDetails.getUsername());

        Role user_Role = userDetails.getRole();
        Boolean isEmailChanged =userDetails.getIsEmailChanged();

        System.out.println("************************************");
        System.out.println(token);
        System.out.println("************************************");
        return new LoginResponse(token,user_Role,isEmailChanged);
    }

    /////////////////////// SIGN UP //////////////////////
    public ResponseEntity<ApiResponse> register(UserDTO user){
        if (userService.verifyUserExisting(user.getUsername())){
            return ResponseEntity.badRequest().body(new ApiResponse(false,"user Already Exist"));
        }
        userService.register(user);
        return ResponseEntity.ok(new ApiResponse(true,"user created successfully"));
    }
}
