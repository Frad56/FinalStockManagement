package com.example.store.service.stockManagment.interfaces;

import com.example.store.dto.stockManagment.ProductUnitPurchaseDTO;
import com.example.store.dto.stockManagment.ProductUnitSaleDTO;
import com.example.store.model.stockManagement.ProductUnitPurchase;
import com.example.store.model.stockManagement.ProductUnitSale;

import java.util.List;

public interface ProductUnitPurchaseService {

    ProductUnitPurchase saveProductUnitSale(ProductUnitPurchaseDTO productUnitPurchaseDTO);
    ProductUnitPurchase   findProductUnitPurchaseById(Long productUnitPurchaseId);
    ProductUnitPurchase   updateProductUnitSale(ProductUnitPurchaseDTO productUnitPurchase,Long productUnitPurchaseId);
    void deleteProductUnitPurchaseById(Long productUnitPurchaseId);
    List<ProductUnitPurchase> fetchProductUnitPurchaseList();

    List<ProductUnitPurchase> findAllByProductVariantId(Long productVariantId);
}
