package com.example.store.model.businessPartnerManagement.supplierManagement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;
    private Double puht;
    private Double remisePct;

    private Double htNet;
    private Double tvaItem;
    private Double ttcNet;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bill_id")
    private Bill bill;


}