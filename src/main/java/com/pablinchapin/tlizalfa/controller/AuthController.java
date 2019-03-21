/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.controller;

import com.pablinchapin.tlizalfa.entity.AuthProvider;
import com.pablinchapin.tlizalfa.entity.User;
import com.pablinchapin.tlizalfa.payload.ApiResponse;
import com.pablinchapin.tlizalfa.payload.AuthResponse;
import com.pablinchapin.tlizalfa.payload.LoginRequest;
import com.pablinchapin.tlizalfa.payload.SignUpRequest;
import com.pablinchapin.tlizalfa.repository.UserRepository;
import com.pablinchapin.tlizalfa.security.TokenProvider;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author pvargas
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private TokenProvider tokenProvider;
    
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
    
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
    
        String token = tokenProvider.createToken(authentication);
        
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
    
        User user = new User();
        
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        User result = userRepository.save(user);
        
        
    
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
        
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully!"));
                
    }
    
    
    
}
