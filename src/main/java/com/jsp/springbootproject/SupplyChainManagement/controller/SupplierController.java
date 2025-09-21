package com.jsp.springbootproject.SupplyChainManagement.controller;

import java.util.List;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Supplier;
import com.jsp.springbootproject.SupplyChainManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(@PathVariable int id) {
        return supplierService.getSupplierById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteSupplier(@PathVariable int id) {
        return supplierService.deleteSupplier(id);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProductId(@PathVariable int productId) {
        return supplierService.getSupplierByProductId(productId);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {
        return supplierService.getSupplierByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
