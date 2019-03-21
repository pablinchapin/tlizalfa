/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.repository;

import com.pablinchapin.tlizalfa.entity.Product;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pvargas
 */
public interface ProductRepository extends CrudRepository<Product, Long>{
    
    Page<Product> findAllByCategoryId(Pageable pageable, Long categoryId);
    //<optional>Product findById(Long productId);
    
}
