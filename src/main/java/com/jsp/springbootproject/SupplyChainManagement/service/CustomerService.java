package com.jsp.springbootproject.SupplyChainManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.springbootproject.SupplyChainManagement.dao.CustomerDao;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Customer;
import com.jsp.springbootproject.SupplyChainManagement.exception.IdNotFoundException;
import com.jsp.springbootproject.SupplyChainManagement.exception.NoRecordAvailableException;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
        ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Customer added successfully");
        res.setData(customerDao.saveCustomer(customer));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomers() {
        List<Customer> list = customerDao.getAllCustomers();
        if(list.isEmpty()) throw new NoRecordAvailableException("No customers available");
        ResponseStructure<List<Customer>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All customers retrieved");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(Integer id) {
        Optional<Customer> opt = customerDao.getCustomerById(id);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(opt.isPresent()) {
            res.setData(opt.get());
            res.setMessage("Customer found");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Customer not found with id: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<String>> deleteCustomer(Integer id) {
        Optional<Customer> opt = customerDao.getCustomerById(id);
        ResponseStructure<String> res = new ResponseStructure<>();
        if(opt.isPresent()) {
            customerDao.deleteCustomer(opt.get());
            res.setData("Record with id: "+id+" has been deleted");
            res.setMessage("Deleted customer");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Customer not found with id: " + id);
        }
    }

    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
        ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setData(customerDao.updateCustomer(customer));
        res.setMessage("Customer updated successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(long phone) {
        Optional<Customer> opt = customerDao.getCustomerByPhoneNumber(phone);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(opt.isPresent()) {
            res.setData(opt.get());
            res.setMessage("Customer found by phone number");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Customer not found with phone: " + phone);
    }

    public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(int orderId) {
        Optional<Customer> opt = customerDao.getCustomerByOrder(orderId);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(opt.isPresent()) {
            res.setData(opt.get());
            res.setMessage("Customer found by order id");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Customer not found for order id: " + orderId);
    }

    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomersByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        Page<Customer> pages = customerDao.getCustomerByPaginationAndSorting(pageNumber, pageSize, field);
        if(pages.isEmpty()) throw new NoRecordAvailableException("No records available");
        ResponseStructure<Page<Customer>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Customers retrieved with pagination");
        res.setData(pages);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
