package com.example.store.model.salesManagement;

import com.example.store.model.stockManagement.ProductVariant;
import com.example.store.model.stockManagement.Unit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;




@Entity
@Table(name = "sales_order_line")
@Data
@NoArgsConstructor
public class SalesOrderLine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesOrderLineId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sales_order_id",nullable = false)
    private SalesOrder salesOrder;

    @JsonProperty("salesOrderId")
    public Long getSalesOrderId() {
        return salesOrder != null ? salesOrder.getSalesOrderId() : null;
    }

//    @JsonProperty("TotalAmount")
//    public void setTotalAmount(BigDecimal totalAmount) {
//        this.totalAmount = totalAmount;
//    }

    @ManyToOne
    @JoinColumn(name = "product_variant_id", nullable = false)
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumn(name = "unitId",nullable = true)
    private Unit unit;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;


    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name ="discount")
    private BigDecimal discount;

    @Column(name = "total_after_discount", precision = 10, scale = 2)
    private BigDecimal totalAfterDiscount;
}
