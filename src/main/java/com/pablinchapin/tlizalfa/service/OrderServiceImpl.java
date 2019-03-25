/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablinchapin.tlizalfa.service;

import com.pablinchapin.tlizalfa.entity.Order;
import com.pablinchapin.tlizalfa.exception.ResourceNotFoundException;
import com.pablinchapin.tlizalfa.repository.OrderRepository;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pvargas
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    
    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
    
    
    @Override
    public Page<Order> getOrdersByCustomerId(Pageable pageable, Long customerId) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public Order getOrderDetail(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    
    
}
