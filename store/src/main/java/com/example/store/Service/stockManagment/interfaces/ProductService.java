package com.example.store.service.stockManagment.interfaces;


import com.example.store.dto.stockManagment.ProductDTO;
import com.example.store.dto.stockManagment.request.CategoryRequest;
import com.example.store.dto.stockManagment.request.DesignationRequest;
import com.example.store.dto.stockManagment.request.ReferenceRequest;
import com.example.store.model.stockManagement.Product;

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
