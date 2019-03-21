/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author pvargas
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    private final long MAX_AGE_SECS = 3600;
    
    
    @Bean
    public MessageSource messageSource(){
        
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        
        messageSource.setBasename("classpath:validation");
        messageSource.setDefaultEncoding("UTF-8");
        
        return messageSource;
    
    }
    
    
    public void addCorsMappings(CorsRegistry registry){
    
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
                
    
    }
    
    
}
