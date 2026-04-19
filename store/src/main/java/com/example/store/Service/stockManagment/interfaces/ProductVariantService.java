package com.example.store.Service.stockManagment.interfaces;


import com.example.store.DTO.stockManagment.ProductDTO;
import com.example.store.DTO.stockManagment.ProductVariantDTO;
import com.example.store.DTO.stockManagment.request.CategoryRequest;
import com.example.store.DTO.stockManagment.request.DesignationRequest;
import com.example.store.DTO.stockManagment.request.ReferenceRequest;
import com.example.store.Model.StockMangement.Product;
import com.example.store.Model.StockMangement.ProductVariant;

import java.util.List;
import java.util.Optional;

public interface ProductVariantService {

    ProductVariant saveProductVariant(ProductVariantDTO productVariantDTO);

    List<ProductVariant> fetchProductVariantList();

    ProductVariant findProductVariantById(Long idProductVariant);

    ProductVariant updateProductVariant(ProductVariantDTO productVariantDTO, Long ProductVariantId);

    void deleteProductVariantById(Long ProductVariantId);

    boolean hasVariantsByProductId(Long productId);
    boolean hasVariantsByProductReference(ReferenceRequest reference);
    boolean hasVariantsByProductDesignation(DesignationRequest designation);
    boolean hasVariantsByProductCategoryName(CategoryRequest categoryName);

    List<ProductVariant> findByProduct_ProductId(Long productId);

    List<ProductVariant> findProductVariantListByProductReference(String product_reference);

    List<ProductVariant> findProductVariantListByProductDesignation(String product_designation);

    List<ProductVariant> findProductVariantListByCategoryName(String category_name);


    List<Product> findProductByReferenceStartingWithIgnoreCase(ReferenceRequest keyword);

    List<Product> findProductByDesignationStartingWithIgnoreCase(DesignationRequest keyword);

    List<Product> findProductByCategoryNameStartingWithIgnoreCase(CategoryRequest keyword);

}
