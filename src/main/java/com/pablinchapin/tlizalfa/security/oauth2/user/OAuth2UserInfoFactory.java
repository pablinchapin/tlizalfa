/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.security.oauth2.user;

import com.pablinchapin.tlizalfa.entity.AuthProvider;
import com.pablinchapin.tlizalfa.exception.OAuth2AuthenticationProcessingException;
import java.util.Map;

/**
 *
 * @author pvargas
 */
public class OAuth2UserInfoFactory {
    
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes){
        
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())){
            return new GoogleOAuth2UserInfo(attributes);
        }else if(registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())){
            return new FacebookOAuth2UserInfo(attributes);
        }else if(registrationId.equalsIgnoreCase(AuthProvider.github.toString())){
            return new GithubOAuth2UserInfo(attributes);
        }else{
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with "+ registrationId + " is not supported");
        }
        
    }
    
}
