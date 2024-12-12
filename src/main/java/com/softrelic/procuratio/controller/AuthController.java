package com.softrelic.procuratio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softrelic.procuratio.domain.User;
import com.softrelic.procuratio.dto.LoginRequestDTO;
import com.softrelic.procuratio.dto.SignupRequestDTO;
import com.softrelic.procuratio.service.CustomUserDetailsService;
import com.softrelic.procuratio.service.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/protected")
    public String protectedRoute() {
        return "This is a protected route. You are authenticated!";
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@Validated @RequestBody SignupRequestDTO signupRequestDTO) {
        User user = userDetailsService.saveUser(signupRequestDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Signup Successful");

        Map<String, String> data = new HashMap<>();
        data.put("email", user.getEmail());
        response.put("data", data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(loginRequestDTO.getEmail());
        Map<String, Object> response = new HashMap<>();

        response.put("message", "Signin successful!");

        Map<String, String> data = new HashMap<>();
        data.put("email", loginRequestDTO.getEmail());
        data.put("token", token);

        response.put("data", data);

        return ResponseEntity.ok(response);

    }

}
