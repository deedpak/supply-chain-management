package com.jsp.springbootproject.SupplyChainManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.springbootproject.SupplyChainManagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.supplier.id = ?1")
    List<Product> getProductsBySupplierId(int supplierId);


    List<Product> findByStockQuantityGreaterThan(int stockQuantity);
}
