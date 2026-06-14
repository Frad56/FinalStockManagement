package com.example.store.service.BusinessPartnerManagement.supplierManagement;

import com.example.store.dto.businessPartner.supplierManagement.SupplierDTO;
import com.example.store.model.businessPartnerManagement.supplierManagement.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier saveSupplier(SupplierDTO supplier);

    Supplier findSupplierById(Long supplierId);

    List<Supplier> fetchSupplierList();

    void deleteSupplierByID(Long supplierId);

     Supplier updateSupplier(SupplierDTO supplierDTO , Long supplierId);
}
