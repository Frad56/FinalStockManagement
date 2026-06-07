package com.example.store.repository.purchaseManagement;


import com.example.store.model.purchaseManagement.PurchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {


    @Query("SELECT po FROM PurchaseOrder po WHERE po.status <> 'DELIVERED'")
    List<PurchaseOrder> findPurchaseOrderListNotDelivered();


}

