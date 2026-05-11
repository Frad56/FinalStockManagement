package com.example.store.Model.StockMangement.MovementInStock;

import com.example.store.Model.PurchaseManagement.PurchaseOrder;
import com.example.store.Model.PurchaseManagement.PurchaseOrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("PURCHASE")
public class PurchaseStockMovement extends MovementInStock{

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "purchase_order_line_id")
    private PurchaseOrderLine purchaseOrderLine;

}
