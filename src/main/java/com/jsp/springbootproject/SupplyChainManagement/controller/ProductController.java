package com.jsp.springbootproject.SupplyChainManagement.controller;

import java.util.List;
import com.jsp.springbootproject.SupplyChainManagement.dto.ResponseStructure;
import com.jsp.springbootproject.SupplyChainManagement.entity.Product;
import com.jsp.springbootproject.SupplyChainManagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/by-supplier/{supplierId}")
    public ResponseEntity<ResponseStructure<List<Product>>> getProductsBySupplierId(@PathVariable int supplierId) {
        return productService.getProductsBySupplierId(supplierId);
    }

    @GetMapping("/by-stock/{stock}")
    public ResponseEntity<ResponseStructure<List<Product>>> getProductsByStockQuantity(@PathVariable int stock) {
        return productService.getProductsByStockQuantity(stock);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Product>>> getProductByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {
        return productService.getProductByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
