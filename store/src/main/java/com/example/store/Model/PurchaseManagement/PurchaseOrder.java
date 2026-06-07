package com.example.store.model.purchaseManagement;

import com.example.store.model.businessPartnerManagement.supplierManagement.Supplier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name= "purchase_order")
public class PurchaseOrder{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderId;

    @ManyToOne
    @JoinColumn(name = "supplier_id",nullable = false)
    private Supplier supplier;



    @CreationTimestamp
    @Column(updatable = false, name = "order_date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;


}

