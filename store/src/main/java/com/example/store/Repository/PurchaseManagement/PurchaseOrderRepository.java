package com.example.store.Repository.PurchaseManagement;


import com.example.store.Model.PurchaseManagement.PurchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {


}
