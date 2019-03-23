/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.util;

import com.pablinchapin.tlizalfa.model.CartInfo;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author pvargas
 */
public class CartUtils {
    
    
    public static CartInfo getCartInSession(HttpServletRequest request){
    
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("newCart");
        
        if(cartInfo == null){
            cartInfo = new CartInfo();
            
            request.getSession().setAttribute("newCart", cartInfo);
        }
        
    return cartInfo;
    }
    
    
    public static void removeCartInSession(HttpServletRequest request){
        request.getSession().removeAttribute("newCart");
    }
    
    
    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo){
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }
    
    
    public static CartInfo getLastOrderedCartInSession(HttpServletRequest request){
        return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
    }
    
    
}
