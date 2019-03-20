/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author pvargas
 */

@ConfigurationProperties(prefix = "app")
public class AppProperties {
    
    private final Auth auth = new Auth();
    private final OAuth2 oAuth2 = new OAuth2();
    
    
    public static class Auth{
    
        private String tokenSecret;
        private long tokenExpirationMSec;

        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMSec() {
            return tokenExpirationMSec;
        }

        public void setTokenExpirationMSec(long tokenExpirationMSec) {
            this.tokenExpirationMSec = tokenExpirationMSec;
        }
        
    }
    
    
    public static class OAuth2 {
    
        private List<String> authorizedRedirectUris = new ArrayList<>();
        
        public List<String> getAuthorizedRedirectUris(){
            return authorizedRedirectUris;
        }

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
        
    }

    public Auth getAuth() {
        return auth;
    }

    public OAuth2 getoAuth2() {
        return oAuth2;
    }

    
    
    
    
}
