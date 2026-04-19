package com.example.store.Model.supplierManagement;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;


    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<ProductSupplier> product_suppliers = new ArrayList<>();
}
