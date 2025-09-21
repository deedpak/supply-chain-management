package com.jsp.springbootproject.SupplyChainManagement.controller;

import java.util.List;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Customer;
import com.jsp.springbootproject.SupplyChainManagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteCustomer(@PathVariable Integer id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(@PathVariable long phone) {
        return customerService.getCustomerByPhone(phone);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(@PathVariable int orderId) {
        return customerService.getCustomerByOrderId(orderId);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {
        return customerService.getCustomersByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
