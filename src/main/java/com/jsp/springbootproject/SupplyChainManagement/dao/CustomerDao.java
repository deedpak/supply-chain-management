package com.jsp.springbootproject.SupplyChainManagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.springbootproject.SupplyChainManagement.entity.Customer;
import com.jsp.springbootproject.SupplyChainManagement.repository.CustomerRepository;

@Repository
public class CustomerDao {

    @Autowired
    private CustomerRepository customerRepository;
    
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }
    
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }
    
    public Optional<Customer> getCustomerByPhoneNumber(long phone) {
        return customerRepository.getCustomerByPhoneNumber(phone);
    }
    
    public Optional<Customer> getCustomerByOrder(int orderId) {
        return customerRepository.getCustomerByOrder(orderId);
    }
    
    public Page<Customer> getCustomerByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        return customerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
    }
}
