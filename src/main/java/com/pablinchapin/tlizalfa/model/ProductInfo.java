/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.model;

import com.pablinchapin.tlizalfa.entity.Product;

/**
 *
 * @author pvargas
 */
public class ProductInfo {
    
    
    private Long id;
    private Long categoryId;
    private String name;
    private String description;
    private String nutritionalInfo;
    private String brand;
    private double price;
    private double tax;
    private boolean active;
    private String frontImageUrl;
    private String frontThumbImageUrl;

    public ProductInfo() {
    }

    public ProductInfo(Long id, Long categoryId, String name, String description, String nutritionalInfo, String brand, double price, double tax, boolean active, String frontImageUrl, String frontThumbImageUrl) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.nutritionalInfo = nutritionalInfo;
        this.brand = brand;
        this.price = price;
        this.tax = tax;
        this.active = active;
        this.frontImageUrl = frontImageUrl;
        this.frontThumbImageUrl = frontThumbImageUrl;
    }

    public ProductInfo(Product product) {
        this.id = product.getId();
        this.categoryId = product.getCategoryId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.nutritionalInfo = product.getNutritionalInfo();
        this.brand = product.getBrand();
        this.price = product.getPrice();
        this.tax = product.getTax();
        this.active = product.isActive();
        this.frontImageUrl = product.getFrontImageUrl();
        this.frontThumbImageUrl = product.getFrontThumbImageUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNutritionalInfo() {
        return nutritionalInfo;
    }

    public void setNutritionalInfo(String nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFrontImageUrl() {
        return frontImageUrl;
    }

    public void setFrontImageUrl(String frontImageUrl) {
        this.frontImageUrl = frontImageUrl;
    }

    public String getFrontThumbImageUrl() {
        return frontThumbImageUrl;
    }

    public void setFrontThumbImageUrl(String frontThumbImageUrl) {
        this.frontThumbImageUrl = frontThumbImageUrl;
    }
    
    
        
}
