/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *
 * @author pvargas
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
    
    @Override
    public void commence(
            HttpServletRequest request, 
            HttpServletResponse response, 
            AuthenticationException ae
    ) throws IOException, ServletException {
        
        logger.error("Responding wit unauthorized error. Message - {} ", ae.getMessage());
        
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ae.getLocalizedMessage());
        
    }
    
}
