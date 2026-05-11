package com.example.store.Model.BusinessPartnerManagement.supplierManagement;


import com.example.store.Model.BusinessPartnerManagement.BusinessPartner;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "supplier")
public class Supplier extends BusinessPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(name = "company_name")
    protected String companyName;

    @Column(name = "contact_name")
    protected String contactName;


//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<ProductSupplier> product_suppliers = new ArrayList<>();
}
