package com.example.store.repository.businessPartner.supplierManagement;

import com.example.store.model.businessPartnerManagement.supplierManagement.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductSupplierRepository extends JpaRepository<ProductSupplier,Long> {
}
