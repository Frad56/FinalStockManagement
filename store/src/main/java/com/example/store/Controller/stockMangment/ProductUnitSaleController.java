package com.example.store.controller.stockMangment;


import com.example.store.dto.stockManagment.ProductUnitSaleDTO;
import com.example.store.model.stockManagement.ProductUnitSale;
import com.example.store.service.salesManagement.interfaces.ProductUnitSaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productUnitSale")
public class ProductUnitSaleController {
    private final ProductUnitSaleService productUnitSaleService;


    public ProductUnitSaleController(ProductUnitSaleService productUnitSaleService) {
        this.productUnitSaleService = productUnitSaleService;
    }

    @PostMapping("/addProductUnitSale")
    public ResponseEntity<ProductUnitSale> saveProductUnitSale(@RequestBody ProductUnitSaleDTO productUnitSaleDTO) {
        return ResponseEntity.ok(productUnitSaleService.saveProductUnitSale(productUnitSaleDTO));
    }
    @GetMapping("/ListProductUnitSale")
    public ResponseEntity<List<ProductUnitSale>> fetchProductUnitSaleList() {
        return ResponseEntity.ok(productUnitSaleService.fetchProductUnitSaleList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductUnitSale> findProductUnitSaleById(@PathVariable("id") Long productUnitSaleId
    ) {
        return ResponseEntity.ok(productUnitSaleService.findProductUnitSaleById(productUnitSaleId));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductUnitSale> updateProductUnitSale(@RequestBody  ProductUnitSaleDTO productUnitSale
                                                                     , @PathVariable("id") Long productUnitSaleId) {
        return ResponseEntity.ok(productUnitSaleService.updateProductUnitSale(productUnitSale, productUnitSaleId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProductUnitSaleById(@PathVariable("id") Long productUnitSaleId) {
        productUnitSaleService.deleteProductUnitSaleById(productUnitSaleId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @GetMapping("/findByProductVariant/{productVariantId}")
    public ResponseEntity<List<ProductUnitSale>> findAllByProductId(@PathVariable("productVariantId") Long productVariantId) {
        return ResponseEntity.ok(productUnitSaleService.findAllByProductVariantId(productVariantId));
    }
}
