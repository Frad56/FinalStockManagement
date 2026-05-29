package com.example.store.service.BusinessPartnerManagement.supplierManagement;

import com.example.store.dto.BusinessPartner.supplierManagement.ProductSupplierDTO;
import com.example.store.model.businessPartnerManagement.supplierManagement.ProductSupplier;

import java.util.List;

public interface ProductSupplierService {

    ProductSupplier saveProductSupplier(ProductSupplierDTO productSupplier);

    ProductSupplier findProductSupplierById(Long productSupplierId );

    List<ProductSupplier> fetchProductSupplierList();

    void deleteProductSupplierById(Long productSupplierId);

}
