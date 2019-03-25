/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.service;

import com.pablinchapin.tlizalfa.entity.Order;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author pvargas
 */

@Validated
public interface OrderService {
    
    @NotNull
    Iterable<Order> getAllOrders();
    
    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);
    
    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
    
}
