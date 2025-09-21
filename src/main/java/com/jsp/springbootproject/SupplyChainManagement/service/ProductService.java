package com.jsp.springbootproject.SupplyChainManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.springbootproject.SupplyChainManagement.dao.ProductDao;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Product;
import com.jsp.springbootproject.SupplyChainManagement.exception.IdNotFoundException;
import com.jsp.springbootproject.SupplyChainManagement.exception.NoRecordAvailableException;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
        ResponseStructure<Product> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Product added successfully");
        res.setData(productDao.saveProduct(product));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts() {
        List<Product> list = productDao.getAllProducts();
        if(list.isEmpty()) throw new NoRecordAvailableException("No products available");
        ResponseStructure<List<Product>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All products retrieved");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Product>> getProductById(int id) {
        Optional<Product> opt = productDao.getProductById(id);
        if(opt.isPresent()) {
            ResponseStructure<Product> res = new ResponseStructure<>();
            res.setData(opt.get());
            res.setMessage("Product found");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Product not found with id: " + id);
    }

    public ResponseEntity<ResponseStructure<String>> deleteProduct(int id) {
        Optional<Product> opt = productDao.getProductById(id);
        if(opt.isPresent()) {
            productDao.deleteProduct(opt.get());
            ResponseStructure<String> res = new ResponseStructure<>();
            res.setData("Product with id: " + id + " deleted successfully");
            res.setMessage("Deleted product");
            res.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else throw new IdNotFoundException("Product not found with id: " + id);
    }

    public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
        ResponseStructure<Product> res = new ResponseStructure<>();
        res.setData(productDao.updateProduct(product));
        res.setMessage("Product updated successfully");
        res.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Product>>> getProductsBySupplierId(int supplierId) {
        List<Product> list = productDao.getProductsBySupplierId(supplierId);
        if(list.isEmpty()) throw new NoRecordAvailableException("No products found for supplier id: " + supplierId);
        ResponseStructure<List<Product>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Products retrieved by supplier id");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<List<Product>>> getProductsByStockQuantity(int stock) {
        List<Product> list = productDao.getProductsByStockQuantity(stock);
        if(list.isEmpty()) throw new NoRecordAvailableException("No products found with stock greater than: " + stock);
        ResponseStructure<List<Product>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Products retrieved by stock quantity");
        res.setData(list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        Page<Product> pages = productDao.getProductByPaginationAndSorting(pageNumber, pageSize, field);
        if(pages.isEmpty()) throw new NoRecordAvailableException("No products available");
        ResponseStructure<Page<Product>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Products retrieved with pagination");
        res.setData(pages);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
