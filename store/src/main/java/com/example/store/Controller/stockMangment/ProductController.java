package com.example.store.controller.stockMangment;


import com.example.store.dto.stockManagment.ProductDTO;

import com.example.store.dto.stockManagment.request.CategoryRequest;
import com.example.store.dto.stockManagment.request.DesignationRequest;
import com.example.store.dto.stockManagment.request.ReferenceRequest;
import com.example.store.model.stockManagement.Product;

import com.example.store.service.stockManagment.interfaces.CategoryService;
import com.example.store.service.stockManagment.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product")
public class ProductController {


    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService
                            ){
        this.productService =productService;

    }


    @PostMapping("/addProduct")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductDTO dto) {
       return ResponseEntity.ok(productService.saveProduct(dto));
    }


    @GetMapping("/ListProducts")
    public ResponseEntity< List<Product> >fetchProductList(){

        List<Product> products =productService.fetchProductList();;
        return ResponseEntity.ok(products);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long productId){
        Product product =  productService.findProductById(productId);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProductByID(@PathVariable("id") Long productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProductBuId(@RequestBody ProductDTO productDTO,
                                                     @PathVariable("id") Long productId){
        Product updatedProduct = productService.updateProduct(productDTO,productId);
        return  ResponseEntity.ok(updatedProduct);
    }
    @PostMapping("/searchByReference")
    public ResponseEntity<List<Product>> searchProductsByReference(@RequestBody ReferenceRequest keyword){
        return ResponseEntity.ok(productService.findByReferenceStartingWithIgnoreCase(keyword));
    }

    @PostMapping("/searchByDesignation")
    public  ResponseEntity<List<Product>> findByDesignation(@RequestBody DesignationRequest keyword){
        return ResponseEntity.ok(productService.findByDesignationStartingWithIgnoreCase(keyword));
    }

    @PostMapping("/searchByCategoryName")
    public  ResponseEntity<List<Product>>findByCategoryName(@RequestBody CategoryRequest keyword){
        return ResponseEntity.ok(productService.findByCategoryNameStartingWithIgnoreCase(keyword));
    }

}
