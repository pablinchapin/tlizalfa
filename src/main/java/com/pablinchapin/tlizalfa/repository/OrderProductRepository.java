/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.repository;

import com.pablinchapin.tlizalfa.entity.OrderProduct;
import com.pablinchapin.tlizalfa.entity.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author pvargas
 */
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK>{
    
}
