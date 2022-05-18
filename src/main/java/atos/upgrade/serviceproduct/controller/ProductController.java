package atos.upgrade.serviceproduct.controller;

import atos.upgrade.serviceproduct.entities.Category;
import atos.upgrade.serviceproduct.entities.Product;
import atos.upgrade.serviceproduct.service.ProductService;
import atos.upgrade.serviceproduct.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products;
        if (null ==  categoryId){
            products = productService.listAllProduct();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(Math.toIntExact(categoryId)).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(products);
    }
        @GetMapping("/{id}")
      public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = productService.getProduct(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productDB = productService.updateProduct(product);
        if (productDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = productService.deleteProduct(id);
        if (productDelete == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }
    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(name = "quantity") Double quantity){
        Product product = productService.updateStock(id, quantity);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}



