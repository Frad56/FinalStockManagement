package com.example.store.controller.businessPartnerManagement.supplierManagement;


import com.example.store.dto.businessPartner.supplierManagement.ProductSupplierDTO;
import com.example.store.model.businessPartnerManagement.supplierManagement.ProductSupplier;
import com.example.store.service.BusinessPartnerManagement.supplierManagement.ProductSupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productSupplier")
public class ProductSupplierController {

    private final ProductSupplierService productSupplierService;

    @Autowired
    public ProductSupplierController(ProductSupplierService productSupplierService){
        this.productSupplierService =productSupplierService;
    }

    //add
    @PostMapping("/addProductSupplier")
    public ResponseEntity<ProductSupplier> saveProduct(@Valid @RequestBody ProductSupplierDTO dto){
        ProductSupplier return_productSupplier = productSupplierService.saveProductSupplier(dto);
        return ResponseEntity.ok(return_productSupplier);
    }

    //list
    @GetMapping("/productSupplier")
    public ResponseEntity<List<ProductSupplier>> fetchProductSupplierList(){
        List<ProductSupplier> productSuppliers = productSupplierService.fetchProductSupplierList();
        return ResponseEntity.ok(productSuppliers);
    }


    //find
    @GetMapping("/productSupplier/find/{id}")
    public ResponseEntity<ProductSupplier> findProductByID(@PathVariable("id") Long productSupplierId){
        ProductSupplier productSupplier = productSupplierService.findProductSupplierById(productSupplierId);
        return ResponseEntity.ok(productSupplier);
    }

    //delete
    public ResponseEntity<String> deleteProductSupplierById(@PathVariable("id") Long productSupplierID){
        productSupplierService.deleteProductSupplierById(productSupplierID);
        return ResponseEntity.ok("Deleted successfully ");
    }


}
