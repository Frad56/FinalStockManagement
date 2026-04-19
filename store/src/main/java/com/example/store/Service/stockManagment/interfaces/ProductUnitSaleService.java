package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.ProductUnitSaleDTO;
import com.example.store.Model.StockMangement.ProductUnitSale;

import java.util.List;

public interface ProductUnitSaleService {

    ProductUnitSale   saveProductUnitSale(ProductUnitSaleDTO productUnitSale);
    ProductUnitSale   findProductUnitSaleById(Long productUnitSaleId);
    ProductUnitSale   updateProductUnitSale(ProductUnitSaleDTO productUnitSale,Long productUnitSaleId);
    void deleteProductUnitSaleById(Long productUnitSaleId);
    List<ProductUnitSale> fetchProductUnitSaleList();

}
