package com.example.store.Model.salesManagement;

import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@Table(name= "sales_order")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesOrderId;

    @CreationTimestamp
    @Column(updatable = false, name = "sales_order_date")
    private LocalDateTime salesOrderDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;


    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


    @JsonManagedReference
    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesOrderLine> orderLines;


}
