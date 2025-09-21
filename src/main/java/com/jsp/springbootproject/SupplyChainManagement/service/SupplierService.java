package com.jsp.springbootproject.SupplyChainManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.springbootproject.SupplyChainManagement.dao.SupplierDao;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Supplier;
import com.jsp.springbootproject.SupplyChainManagement.exception.IdNotFoundException;
import com.jsp.springbootproject.SupplyChainManagement.exception.NoRecordAvailableException;

@Service
public class SupplierService {

    @Autowired
    private SupplierDao supplierDao;

    public ResponseEntity<ResponseStructure<Supplier>> saveSupplier(Supplier supplier) {
        ResponseStructure<Supplier> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Supplier added successfully");
        res.setData(supplierDao.saveSupplier(supplier));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Supplier>>> getAllSuppliers() {
        List<Supplier> list = supplierDao.getAllSuppliers();
        if(list.isEmpty()) throw new NoRecordAvailableException("No suppliers available");
        ResponseStructure<List<Supplier>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All suppliers retrieved");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Supplier>> getSupplierById(int id) {
        Optional<Supplier> opt = supplierDao.getSupplierById(id);
        if(opt.isPresent()) {
            ResponseStructure<Supplier> res = new ResponseStructure<>();
            res.setData(opt.get());
            res.setMessage("Supplier found");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Supplier not found with id: " + id);
    }

    public ResponseEntity<ResponseStructure<String>> deleteSupplier(int id) {
        Optional<Supplier> opt = supplierDao.getSupplierById(id);
        if(opt.isPresent()) {
            supplierDao.deleteSupplier(opt.get());
            ResponseStructure<String> res = new ResponseStructure<>();
            res.setData("Supplier with id: " + id + " deleted successfully");
            res.setMessage("Deleted supplier");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Supplier not found with id: " + id);
    }

    public ResponseEntity<ResponseStructure<Supplier>> updateSupplier(Supplier supplier) {
        ResponseStructure<Supplier> res = new ResponseStructure<>();
        res.setData(supplierDao.updateSupplier(supplier));
        res.setMessage("Supplier updated successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Supplier>> getSupplierByProductId(int productId) {
        Optional<Supplier> opt = supplierDao.getSupplierByProductId(productId);
        if(opt.isPresent()) {
            ResponseStructure<Supplier> res = new ResponseStructure<>();
            res.setData(opt.get());
            res.setMessage("Supplier found by product id");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Supplier not found for product id: " + productId);
    }

    public ResponseEntity<ResponseStructure<Page<Supplier>>> getSupplierByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        Page<Supplier> pages = supplierDao.getSupplierByPaginationAndSorting(pageNumber, pageSize, field);
        if(pages.isEmpty()) throw new NoRecordAvailableException("No suppliers available");
        ResponseStructure<Page<Supplier>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Suppliers retrieved with pagination");
        res.setData(pages);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
