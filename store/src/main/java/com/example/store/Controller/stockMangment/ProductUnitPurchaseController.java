package com.example.store.controller.stockMangment;


import com.example.store.dto.stockManagment.ProductUnitPurchaseDTO;
import com.example.store.dto.stockManagment.ProductUnitSaleDTO;
import com.example.store.model.stockManagement.ProductUnitPurchase;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.service.stockManagment.interfaces.ProductUnitPurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productUnitPurchase")
public class ProductUnitPurchaseController {
    private final ProductUnitPurchaseService productUnitPurchaseService;

    public ProductUnitPurchaseController(ProductUnitPurchaseService productUnitPurchaseService) {
        this.productUnitPurchaseService = productUnitPurchaseService;
    }


    @PostMapping("/addProductUnitPurchase")
    public ResponseEntity<ProductUnitPurchase> saveProductUnitPurchase(@RequestBody ProductUnitPurchaseDTO productUnitPurchaseDTO) {
        return ResponseEntity.ok(productUnitPurchaseService.saveProductUnitSale(productUnitPurchaseDTO));
    }
    @GetMapping("/ListProductUnitPurchase")
    public ResponseEntity<List<ProductUnitPurchase>> fetchProductUnitPurchaseList() {
        return ResponseEntity.ok(productUnitPurchaseService.fetchProductUnitPurchaseList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductUnitPurchase> findProductUnitPurchaseById(@PathVariable("id") Long productUnitSaleId
    ) {
        return ResponseEntity.ok(productUnitPurchaseService.findProductUnitPurchaseById(productUnitSaleId));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductUnitPurchase> updateProductUnitPurchase(@RequestBody  ProductUnitPurchaseDTO productUnitPurchaseDTO, @PathVariable("id") Long productUnitPurchaseId) {
        return ResponseEntity.ok(productUnitPurchaseService.updateProductUnitSale(productUnitPurchaseDTO, productUnitPurchaseId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProductUnitPurchaseById(@PathVariable("id") Long productUnitSaleId) {
        productUnitPurchaseService.deleteProductUnitPurchaseById(productUnitSaleId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @GetMapping("/findByProductVariant/{productVariantId}")
    public ResponseEntity<List<ProductUnitPurchase>> findAllByProductId(@PathVariable("productVariantId") Long productVariantId) {
        return ResponseEntity.ok(productUnitPurchaseService.findAllByProductVariantId(productVariantId));
    }
}
