package com.jsp.springbootproject.SupplyChainManagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.springbootproject.SupplyChainManagement.entity.Supplier;
import com.jsp.springbootproject.SupplyChainManagement.repository.SupplierRepository;

@Repository
public class SupplierDao {

    @Autowired
    private SupplierRepository supplierRepository;
    
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
    
    public Optional<Supplier> getSupplierById(Integer id) {
        return supplierRepository.findById(id);
    }
    
    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }
    
    public Optional<Supplier> getSupplierByCompanyName(String companyName) {
        return supplierRepository.findByCompanyName(companyName);
    }
    
    public Optional<Supplier> getSupplierByProductId(int productId) {
        return supplierRepository.getSupplierByProductId(productId);
    }
    
    public Page<Supplier> getSupplierByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        return supplierRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
    }
}
