package com.javadaily.redditclone.service;

import com.javadaily.redditclone.exceptions.SpringRedditException;
import com.javadaily.redditclone.model.User;
import com.javadaily.redditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /*autowiring the userRepository using the constructor injection*/
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // we need to provide the UserDetails Instance here
       Optional<User> userOptional =  userRepository.findByUsername(userName);
        User user = userOptional.orElseThrow(()-> new UsernameNotFoundException("No username with name " + userName));

        // implementing the UserDetails interface
        // we are returning the UserDetail object here
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(),true,true,true,
                getAuthorities("USER"));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }


}
