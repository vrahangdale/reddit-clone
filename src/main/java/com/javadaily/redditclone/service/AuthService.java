package com.javadaily.redditclone.service;

import com.javadaily.redditclone.dto.RegisterRequest;
import com.javadaily.redditclone.model.NotificationEmail;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.model.VerificationToken;
import com.javadaily.redditclone.repository.UserRepository;
import com.javadaily.redditclone.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

   /* Rather than making it a field injection we are using the constructor injection using lombock
   * this is a better approach and we should usually avoid using the field injection and user constructor
   * injection */
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest){ // registerRequest is just an dto

        User user = new User();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);

         mailService.sendMail(new NotificationEmail("Please activate your account",
                 user.getEmail(),
                 "Thank you for signing up to Spring Reddit, " +
                         "please click on the below url to activate your account : " +
                         "http://localhost:8081/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        return token;
    }

}
