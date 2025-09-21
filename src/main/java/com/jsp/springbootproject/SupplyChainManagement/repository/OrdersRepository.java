package com.jsp.springbootproject.SupplyChainManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.springbootproject.SupplyChainManagement.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

    Optional<Orders> findByTrackingNumber(String trackingNumber);

    List<Orders> findByPriceGreaterThan(double price);

    List<Orders> findByStatus(String status);

    @Query("select o from Orders o where o.customer.id = ?1")
    List<Orders> getOrdersByCustomerId(int customerId);

}
