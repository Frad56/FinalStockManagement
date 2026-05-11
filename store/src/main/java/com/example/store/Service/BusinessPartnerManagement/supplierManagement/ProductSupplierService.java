package com.example.store.Service.BusinessPartnerManagement.supplierManagement;

import com.example.store.DTO.BusinessPartner.supplierManagement.ProductSupplierDTO;
import com.example.store.Model.BusinessPartnerManagement.supplierManagement.ProductSupplier;

import java.util.List;

public interface ProductSupplierService {

    ProductSupplier saveProductSupplier(ProductSupplierDTO productSupplier);

    ProductSupplier findProductSupplierById(Long productSupplierId );

    List<ProductSupplier> fetchProductSupplierList();

    void deleteProductSupplierById(Long productSupplierId);

}
