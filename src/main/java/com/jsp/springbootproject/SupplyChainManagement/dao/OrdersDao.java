package com.jsp.springbootproject.SupplyChainManagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.springbootproject.SupplyChainManagement.entity.Orders;
import com.jsp.springbootproject.SupplyChainManagement.repository.OrdersRepository;

@Repository
public class OrdersDao {

    @Autowired
    private OrdersRepository ordersRepository;
    
    public Orders saveOrder(Orders order) {
        return ordersRepository.save(order);
    }
    
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
    
    public Optional<Orders> getOrderById(Integer id) {
        return ordersRepository.findById(id);
    }
    
    public Orders updateOrder(Orders order) {
        return ordersRepository.save(order);
    }
    
    public void deleteOrder(Orders order) {
        ordersRepository.delete(order);
    }
    
    public Optional<Orders> getOrderByTrackingNumber(String trackingNumber) {
        return ordersRepository.findByTrackingNumber(trackingNumber);
    }
    
    public List<Orders> getOrdersWithAmountGreaterThan(double amount) {
        return ordersRepository.findByPriceGreaterThan(amount);
    }
    
    public List<Orders> getOrdersByStatus(String status) {
        return ordersRepository.findByStatus(status);
    }
    
    public List<Orders> getOrdersByCustomerId(int customerId) {
        return ordersRepository.getOrdersByCustomerId(customerId);
    }
    
    public Page<Orders> getOrdersByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        return ordersRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
    }
}
