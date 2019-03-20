/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.security;

import com.pablinchapin.tlizalfa.entity.User;
import com.pablinchapin.tlizalfa.exception.ResourceNotFoundException;
import com.pablinchapin.tlizalfa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pvargas
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    
    @Autowired
    UserRepository userRepository;
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with emial :"+email));
        
        
        return UserPrincipal.create(user);
    }
    
    
    @Transactional
    public UserDetails loadUsersById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        
        return UserPrincipal.create(user);
    }
    
    
    
}
