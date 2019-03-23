/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.entity;

import com.pablinchapin.tlizalfa.model.CustomerInfo;

/**
 *
 * @author pvargas
 */
public class CustomerForm {
    
    private String name;
    private String address;
    private String email;
    private String phone;
    private boolean valid;

    public CustomerForm() {
    }

    public CustomerForm(CustomerInfo customerInfo) {
        this.name = customerInfo.getName();
        this.address = customerInfo.getAddress();
        this.email = customerInfo.getEmail();
        this.phone = customerInfo.getPhone();
        this.valid = customerInfo.isValid();
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
    
    
}
