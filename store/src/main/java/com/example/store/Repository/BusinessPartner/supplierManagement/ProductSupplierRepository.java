package com.example.store.Repository.BusinessPartner.supplierManagement;

import com.example.store.Model.BusinessPartnerManagement.supplierManagement.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier,Long> {
}
