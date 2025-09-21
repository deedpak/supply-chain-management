package com.jsp.springbootproject.SupplyChainManagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jsp.springbootproject.SupplyChainManagement.entity.Product;
import com.jsp.springbootproject.SupplyChainManagement.repository.ProductRepository;

@Repository
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;
    
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
    
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
    
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
    
    public List<Product> getProductsBySupplierId(int supplierId) {
        return productRepository.getProductsBySupplierId(supplierId);
    }
    
    public List<Product> getProductsByStockQuantity(int stockQuantity) {
        return productRepository.findByStockQuantityGreaterThan(stockQuantity);
    }
    
    public Page<Product> getProductByPaginationAndSorting(int pageNumber, int pageSize, String field) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
    }
}
