package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Optional<User> user = userRepository.findByUsername(username);

        SecurityUser securityUser =  user.map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("username not found "+username) );

         return securityUser;
    }
}
