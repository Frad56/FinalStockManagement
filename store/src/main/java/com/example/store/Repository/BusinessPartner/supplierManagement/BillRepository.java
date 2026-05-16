package com.example.store.Repository.BusinessPartner.supplierManagement;


import com.example.store.Model.BusinessPartnerManagement.supplierManagement.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
}
