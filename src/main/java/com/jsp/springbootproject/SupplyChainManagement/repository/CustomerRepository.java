package com.jsp.springbootproject.SupplyChainManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.springbootproject.SupplyChainManagement.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c where c.phone= ?1")
	Optional <Customer> getCustomerByPhoneNumber(long phone);
	
	@Query("select o.customer from Orders o where o.id=?1")
	Optional <Customer> getCustomerByOrder(int orderId);
}
