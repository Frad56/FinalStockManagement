package com.example.store.service.salesManagement.interfaces;


import com.example.store.dto.stockManagment.ProductUnitSaleDTO;
import com.example.store.model.stockManagement.ProductUnitSale;

import java.util.List;

public interface ProductUnitSaleService {

    ProductUnitSale   saveProductUnitSale(ProductUnitSaleDTO productUnitSale);
    ProductUnitSale   findProductUnitSaleById(Long productUnitSaleId);
    ProductUnitSale   updateProductUnitSale(ProductUnitSaleDTO productUnitSale,Long productUnitSaleId);
    void deleteProductUnitSaleById(Long productUnitSaleId);
    List<ProductUnitSale> fetchProductUnitSaleList();

    List<ProductUnitSale> findAllByProductVariantId(Long productVariantId);

}
