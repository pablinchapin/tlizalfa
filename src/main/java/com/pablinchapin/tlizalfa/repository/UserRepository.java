/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.repository;

import com.pablinchapin.tlizalfa.entity.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pvargas
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    
}
