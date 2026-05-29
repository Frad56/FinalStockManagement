package com.example.store.controller.AuthController;


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
