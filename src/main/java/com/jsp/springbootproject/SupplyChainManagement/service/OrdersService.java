package com.jsp.springbootproject.SupplyChainManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.springbootproject.SupplyChainManagement.dao.OrdersDao;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Orders;
import com.jsp.springbootproject.SupplyChainManagement.exception.IdNotFoundException;
import com.jsp.springbootproject.SupplyChainManagement.exception.NoRecordAvailableException;

@Service
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    public ResponseEntity<ResponseStructure<Orders>> saveOrder(Orders order) {
        ResponseStructure<Orders> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Order created successfully");
        res.setData(ordersDao.saveOrder(order));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrders() {
        List<Orders> list = ordersDao.getAllOrders();
        if(list.isEmpty()) throw new NoRecordAvailableException("No orders available");
        ResponseStructure<List<Orders>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All orders retrieved");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Orders>> getOrderById(int id) {
        Optional<Orders> opt = ordersDao.getOrderById(id);
        if(opt.isPresent()) {
            ResponseStructure<Orders> res = new ResponseStructure<>();
            res.setData(opt.get());
            res.setMessage("Order found");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Order not found with id: " + id);
    }

    public ResponseEntity<ResponseStructure<String>> deleteOrder(int id) {
        Optional<Orders> opt = ordersDao.getOrderById(id);
        if(opt.isPresent()) {
            ordersDao.deleteOrder(opt.get());
            ResponseStructure<String> res = new ResponseStructure<>();
            res.setData("Order with id: " + id + " deleted successfully");
            res.setMessage("Deleted order");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Order not found with id: " + id);
    }

    public ResponseEntity<ResponseStructure<Orders>> updateOrder(Orders order) {
        ResponseStructure<Orders> res = new ResponseStructure<>();
        res.setData(ordersDao.updateOrder(order));
        res.setMessage("Order updated successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(String trackingNumber) {
        Optional<Orders> opt = ordersDao.getOrderByTrackingNumber(trackingNumber);
        if(opt.isPresent()) {
            ResponseStructure<Orders> res = new ResponseStructure<>();
            res.setData(opt.get());
            res.setMessage("Order found by tracking number");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Order not found with tracking number: " + trackingNumber);
    }

    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersWithAmountGreaterThan(double amount) {
        List<Orders> list = ordersDao.getOrdersWithAmountGreaterThan(amount);
        if(list.isEmpty()) throw new NoRecordAvailableException("No orders found with amount greater than: " + amount);
        ResponseStructure<List<Orders>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Orders retrieved with amount greater than " + amount);
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByStatus(String status) {
        List<Orders> list = ordersDao.getOrdersByStatus(status);
        if(list.isEmpty()) throw new NoRecordAvailableException("No orders found with status: " + status);
        ResponseStructure<List<Orders>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Orders retrieved by status");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByCustomerId(int customerId) {
        List<Orders> list = ordersDao.getOrdersByCustomerId(customerId);
        if(list.isEmpty()) throw new NoRecordAvailableException("No orders found for customer id: " + customerId);
        ResponseStructure<List<Orders>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Orders retrieved by customer id");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Page<Orders>>> getOrdersByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        Page<Orders> pages = ordersDao.getOrdersByPaginationAndSorting(pageNumber, pageSize, field);
        if(pages.isEmpty()) throw new NoRecordAvailableException("No orders available");
        ResponseStructure<Page<Orders>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Orders retrieved with pagination");
        res.setData(pages);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
