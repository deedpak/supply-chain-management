package com.jsp.springbootproject.SupplyChainManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.springbootproject.SupplyChainManagement.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    Optional<Supplier> findByCompanyName(String companyName);

    @Query("select s from Supplier s where s.id in (select p.supplier.id from Product p where p.id = ?1)")
    Optional<Supplier> getSupplierByProductId(int productId);
}
