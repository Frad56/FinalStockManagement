package com.example.store.Model.BusinessPartnerManagement.supplierManagement;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorName;
    private String vendorAddress;
    private String vendorMF;
    private String vendorRC;
    private String vendorPhone;

    private String billNumber;
    private LocalDate billDate;

    private String clientName;

    private Double totalBrutHT;
    private Double remise;
    private Double totalHTNet;

    private Double tva;
    private Double tvaPct;
    private Double timbreFiscal;

    private Double totalTTC;
    private Double subtotal;
    private Double tax;
    private Double total;

    private String currency;
    private String rawText;
    private String imageDataUrl;

    private String status;
    private String language;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BillItem> items;


}
