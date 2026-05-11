package com.example.store.Model.BusinessPartnerManagement;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BusinessPartner {



    @Column(name = "phone_number")
    protected String phoneNumber;

    @Column(name = "fax")
    protected String fax;

    @Column(name = "email")
    protected String email;

    @Column(name = "address")
    protected String address;

    @Column(name = "city")
    protected String city;

    @Column(name = "postal_code")
    protected String postalCode;

    @Column(name = "country")
    protected String country;}
