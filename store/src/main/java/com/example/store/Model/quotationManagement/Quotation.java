package com.example.store.model.quotationManagement;


import com.example.store.model.businessPartnerManagement.clientManagment.Client;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name= "quotation")
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quotationId;

    @CreationTimestamp
    @Column(updatable = false, name = "quotation_date")
    private Date quotationDate;


    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = true)
    private Client client;
}
