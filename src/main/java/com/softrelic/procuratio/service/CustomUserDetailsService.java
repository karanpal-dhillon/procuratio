package com.softrelic.procuratio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.softrelic.procuratio.domain.User;
import com.softrelic.procuratio.dto.SignupRequestDTO;
import com.softrelic.procuratio.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    public User saveUser(SignupRequestDTO signupRequestDTO) {
        User user = new User();

        user.setEmail(signupRequestDTO.getEmail());

        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
        return userRepo.save(user);
    }

}