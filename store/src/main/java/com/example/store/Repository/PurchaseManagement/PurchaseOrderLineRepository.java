package com.example.store.Repository.PurchaseManagement;

import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLine,Long> {


    List<PurchaseOrderLine> findByPurchaseOrder_PurchaseOrderId(Long purchaseOrderId);

//
//    @Query("SELECT pol FROM PurchaseOrderLine pol WHERE FUNCTION('DATE', pol.purchaseOrder.orderDate) = :date")
//    List<PurchaseOrderLine> findByPurchaseOrder_orderDate(@Param("date") Date date);


    @Modifying
    @Query("DELETE FROM PurchaseOrderLine pol WHERE pol.purchaseOrder.purchaseOrderId = :purchaseOrderId")
    void deleteByPurchaseOrderId(@Param("purchaseOrderId") Long purchaseOrderId);
}
