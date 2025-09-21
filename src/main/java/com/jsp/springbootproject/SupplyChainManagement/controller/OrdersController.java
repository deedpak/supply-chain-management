package com.jsp.springbootproject.SupplyChainManagement.controller;

import java.util.List;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Orders;
import com.jsp.springbootproject.SupplyChainManagement.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Orders>> saveOrder(@RequestBody Orders order) {
        return ordersService.saveOrder(order);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable int id) {
        return ordersService.getOrderById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody Orders order) {
        return ordersService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteOrder(@PathVariable int id) {
        return ordersService.deleteOrder(id);
    }

    @GetMapping("/by-tracking/{trackingNumber}")
    public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(@PathVariable String trackingNumber) {
        return ordersService.getOrderByTrackingNumber(trackingNumber);
    }

    @GetMapping("/amount-greater/{amount}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersWithAmountGreaterThan(@PathVariable double amount) {
        return ordersService.getOrdersWithAmountGreaterThan(amount);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByStatus(@PathVariable String status) {
        return ordersService.getOrdersByStatus(status);
    }

    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomerId(@PathVariable int customerId) {
        return ordersService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Orders>>> getOrdersByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {
        return ordersService.getOrdersByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
