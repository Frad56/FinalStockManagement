package com.example.store.Controller.AuthController;


import com.example.store.DTO.authentification.UserResponse;
import com.example.store.Security.details.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/Worker")
public class WorkerController {



   /* @GetMapping("/WorkerInformations")
    public ResponseEntity<UserResponse> getWorkerInformations(@AuthenticationPrincipal
                            CustomUserDetailsService userDetailsService){
       return new ResponseEntity<>.Ok().body("user name : "+userDetailsService.getUsername());
    }

    */
}
