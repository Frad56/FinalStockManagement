package com.example.store.service.stockManagment.interfaces;


import com.example.store.dto.stockManagment.ProductVariantDTO;
import com.example.store.dto.stockManagment.request.CategoryRequest;
import com.example.store.dto.stockManagment.request.CodeRequest;
import com.example.store.dto.stockManagment.request.DesignationRequest;
import com.example.store.dto.stockManagment.request.ReferenceRequest;
import com.example.store.model.stockManagement.Product;
import com.example.store.model.stockManagement.ProductVariant;

import java.util.List;

public interface ProductVariantService {

    ProductVariant saveProductVariant(ProductVariantDTO productVariantDTO);

    List<ProductVariant> fetchProductVariantList();

    ProductVariant findProductVariantById(Long idProductVariant);

    ProductVariant updateProductVariantDTO(ProductVariantDTO productVariantDTO, Long productVariantId);
    ProductVariant updateProductVariant(ProductVariant productVariant, Long productVariantId);

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

    List<ProductVariant> findByCodeStartingWithIgnoreCase(CodeRequest keyword);
}
