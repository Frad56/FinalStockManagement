package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.ProductDTO;
import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.DTO.stockManagment.request.CategoryRequest;
import com.example.store.DTO.stockManagment.request.DesignationRequest;
import com.example.store.DTO.stockManagment.request.ReferenceRequest;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;

import java.util.List;


public interface ProductService {

   Product saveProduct(ProductDTO product);

   List<Product> fetchProductList();

   Product findProductById(Long idProduct);

   Product updateProduct(ProductDTO product, Long productId);

   void deleteProductById(Long productID);

   boolean verifyIfProductExistsByReference(String reference);

   boolean verifyIfProductExistsByCategoryName(String name);

   boolean verifyIfProductExistsByDesignation(String designation);

   List<Product> findByReferenceStartingWithIgnoreCase(ReferenceRequest keyword);

   List<Product> findByDesignationStartingWithIgnoreCase(DesignationRequest keyword);

   List<Product> findByCategoryNameStartingWithIgnoreCase(CategoryRequest keyword);
}
