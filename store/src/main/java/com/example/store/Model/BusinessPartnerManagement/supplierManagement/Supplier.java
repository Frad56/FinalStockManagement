package com.example.store.model.businessPartnerManagement.supplierManagement;


import com.example.store.model.businessPartnerManagement.BusinessPartner;
import jakarta.persistence.*;
import lombok.Data;


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

    @Column(name = "tax_identification_number")
    protected String taxIdentificationNumber;

//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<ProductSupplier> product_suppliers = new ArrayList<>();
}
