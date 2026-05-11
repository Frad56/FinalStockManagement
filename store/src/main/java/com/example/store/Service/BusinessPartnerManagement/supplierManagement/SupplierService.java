package com.example.store.Service.BusinessPartnerManagement.supplierManagement;

import com.example.store.DTO.BusinessPartner.supplierManagement.SupplierDTO;
import com.example.store.Model.BusinessPartnerManagement.supplierManagement.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier saveSupplier(SupplierDTO supplier);

    Supplier findSupplierById(Long supplierId);

    List<Supplier> fetchSupplierList();

    void deleteSupplierByID(Long supplierId);

     Supplier updateSupplier(SupplierDTO supplierDTO , Long supplierId);
}
