package com.javadaily.redditclone.controller;

import com.javadaily.redditclone.dto.AuthenticationResponse;
import com.javadaily.redditclone.dto.LoginRequest;
import com.javadaily.redditclone.dto.RegisterRequest;
import com.javadaily.redditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    // here the registerRequest object is the dto we have created
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration Successful", HttpStatus.OK);

    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){

        System.out.println("i am here");
        return authService.login(loginRequest);
    }

}
