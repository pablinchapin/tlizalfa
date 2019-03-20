/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author pvargas
 */
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    
    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
    
    
    
    
    
}
