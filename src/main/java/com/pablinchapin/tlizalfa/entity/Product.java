/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pvargas
 */

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long categoryId;
    
    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(nullable = false, length = 255)
    private String description;
    
    @Column(nullable = false, length = 255)
    private String nutritionalInfo;
    
    @Column(nullable = false, length = 255)
    private String brand;
    
    @Column(nullable = false)
    private double price;
    
    @Column(nullable = false)
    private double tax;
    
    @Column(nullable = false)
    private boolean active;
    
    
    private String frontImageUrl;
    private String frontThumbImageUrl;

    public Product() {
    }

    public Product(Long id, Long categoryId, String name, String description, String nutritionalInfo, String brand, double price, double tax, boolean active, String frontImageUrl, String frontThumbImageUrl) {
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", name=" + name + ", description=" + description + ", nutritionalInfo=" + nutritionalInfo + ", brand=" + brand + ", price=" + price + ", tax=" + tax + ", active=" + active + ", frontImageUrl=" + frontImageUrl + ", frontThumbImageUrl=" + frontThumbImageUrl + '}';
    }
    
    
    
    
    
    
}
