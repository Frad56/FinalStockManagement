package com.example.store.model.stockManagement.MovementInStock;


import com.example.store.model.salesManagement.SalesOrder;
import com.example.store.model.salesManagement.SalesOrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("SALE")
public class SaleStockMovement extends MovementInStock{

    @ManyToOne
    @JoinColumn(name = "sale_order")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "sales_order_line_id")
    private SalesOrderLine salesOrderLine;


}
