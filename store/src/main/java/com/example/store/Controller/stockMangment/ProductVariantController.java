package com.example.store.Controller.stockMangment;


import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.DTO.stockManagment.request.CategoryRequest;
import com.example.store.DTO.stockManagment.request.DesignationRequest;
import com.example.store.DTO.stockManagment.request.ReferenceRequest;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;
import com.example.store.Service.stockManagment.interfaces.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/productVariant")
public class ProductVariantController {
    private final ProductVariantService productVariantService;
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @PostMapping("/addProductVariant")
    public ResponseEntity<ProductVariant> saveProductVariant(@RequestBody  ProductVariantDTO productVariantDTO) {
        return ResponseEntity.ok(productVariantService.saveProductVariant(productVariantDTO));
    }
    @GetMapping("/ListProductVariants")
    public ResponseEntity<List<ProductVariant>> fetchProductVariantList() {
        return ResponseEntity.ok(productVariantService.fetchProductVariantList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductVariant> findProductVariantById(@PathVariable("id") Long productVariantId) {
        return ResponseEntity.ok(productVariantService.findProductVariantById(productVariantId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProductVariantById(@PathVariable("id") Long productVariantId) {
        productVariantService.deleteProductVariantById(productVariantId);
        return ResponseEntity.ok(Map.of("message","Deleted Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(@RequestBody ProductVariantDTO productVariantDTO, @PathVariable("id") Long productVariantId) {
        return ResponseEntity.ok(productVariantService.updateProductVariant(productVariantDTO, productVariantId));
    }

    @GetMapping("/{productId}/has-variants")
    public ResponseEntity<Map<String,Boolean>> hasProductVariants(@PathVariable("productId") Long productId) {
        boolean hasVariants = productVariantService.hasVariantsByProductId(productId);
        return ResponseEntity.ok(Map.of("hasVariants", hasVariants));
    }

    @PostMapping("/hasVariantByReference")
    public ResponseEntity<Map<String,Boolean>> hasProductVariantsByReference(@RequestBody ReferenceRequest keyword){
        boolean hasVariants =productVariantService.hasVariantsByProductReference(keyword);
        return ResponseEntity.ok(Map.of("hasVariants", hasVariants));
    }
    @PostMapping("/hasVariantByDesignation")
    public ResponseEntity<Map<String,Boolean>> hasProductVariantsByDesignation(@RequestBody DesignationRequest keyword){
        boolean hasVariants =productVariantService.hasVariantsByProductDesignation(keyword);
        return ResponseEntity.ok(Map.of("hasVariants", hasVariants));
    }
    @PostMapping("/hasVariantByCategoryName")
    public ResponseEntity<Map<String,Boolean>> hasProductVariantsByCategoryName(@RequestBody CategoryRequest keyword){
        boolean hasVariants =productVariantService.hasVariantsByProductCategoryName(keyword);
        return ResponseEntity.ok(Map.of("hasVariants", hasVariants));
    }

    @GetMapping("/products/{productId}/variants")
    public ResponseEntity<List<ProductVariant>> findByProduct_ProductId(@PathVariable("productId") Long productId) {
        List<ProductVariant> productVariantList = productVariantService.findByProduct_ProductId(productId);
        return ResponseEntity.ok(productVariantList);
    }

    @PostMapping("/findByProductDesignation")
    public ResponseEntity<List<ProductVariant>> findProductVariantListByProductDesignation(@RequestBody DesignationRequest request) {

        return ResponseEntity.ok(productVariantService.findProductVariantListByProductDesignation(request.getProductDesignation()));
    }

    @PostMapping("/findByCategoryName")
    public ResponseEntity<List<ProductVariant>> findProductVariantListByCategoryName(@RequestBody CategoryRequest request) {

        return ResponseEntity.ok(productVariantService.findProductVariantListByCategoryName(request.getProductCategoryName()));
    }

    @PostMapping("/findByProductReference")
    public ResponseEntity<List<ProductVariant>> findProductVariantListByProductReference(@RequestBody ReferenceRequest request) {

        return ResponseEntity.ok(productVariantService.findProductVariantListByProductReference(request.getProductReference()));
    }

    @PostMapping("/searchProductByReference")
    public ResponseEntity<List<Product>> findProductByReference(@RequestBody ReferenceRequest keyword){
        return ResponseEntity.ok(productVariantService.findProductByReferenceStartingWithIgnoreCase(keyword));
    }

    @PostMapping("/searchProductByDesignation")
    public  ResponseEntity<List<Product>> findProductByDesignation(@RequestBody DesignationRequest keyword){
        return ResponseEntity.ok(productVariantService.findProductByDesignationStartingWithIgnoreCase(keyword));
    }

    @PostMapping("/searchProductByCategoryName")
    public  ResponseEntity<List<Product>>findProductByCategoryName(@RequestBody CategoryRequest keyword){
        return ResponseEntity.ok(productVariantService.findProductByCategoryNameStartingWithIgnoreCase(keyword));
    }


}
