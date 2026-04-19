package com.example.store.Controller.AuthController;

import com.example.store.DTO.Response.ApiResponse;
import com.example.store.DTO.authentification.LoginRequest;
import com.example.store.DTO.authentification.LoginResponse;
import com.example.store.DTO.authentification.UserDTO;
import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;
import com.example.store.Security.jwt.JwtUtil;
import com.example.store.Security.details.CustomUserDetailsService;
import com.example.store.Service.AuthService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthController(AuthenticationManager authenticationManager
            ,CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder,
                          UserService userService,
                          JwtUtil jwtUtil){
        this.authenticationManager =authenticationManager;
        this.customUserDetailsService =customUserDetailsService;
        this.passwordEncoder =passwordEncoder;
        this.userService=userService;
        this.jwtUtil=jwtUtil;
    }



    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginRequest user) {
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
            User get_user = userService.findByUsername(user.getUsername());

            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token =jwtUtil.generateToken(userDetails.getUsername());

            Role user_Role = get_user.getRole();

        return new LoginResponse(token,user_Role,get_user.isEmailChanged());

    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDTO user){
        if (userService.verifyUserExisting(user.getUsername())){
            return ResponseEntity.badRequest().body(new ApiResponse(false,"user Already Exist"));
        }
        userService.register(user);
        return ResponseEntity.ok(new ApiResponse(true,"user created successfully"));
    }
}
