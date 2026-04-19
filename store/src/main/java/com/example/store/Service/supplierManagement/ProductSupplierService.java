package com.example.store.Service.supplierManagement;

import com.example.store.DTO.supplierManagement.ProductSupplierDTO;
import com.example.store.Model.supplierManagement.ProductSupplier;

import java.util.List;

public interface ProductSupplierService {

    ProductSupplier saveProductSupplier(ProductSupplierDTO productSupplier);

    ProductSupplier findProductSupplierById(Long productSupplierId );

    List<ProductSupplier> fetchProductSupplierList();

    void deleteProductSupplierById(Long productSupplierId);

}
