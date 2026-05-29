package com.example.store.model.stockManagement.MovementInStock;

import com.example.store.model.purchaseManagement.PurchaseOrder;
import com.example.store.model.purchaseManagement.PurchaseOrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
