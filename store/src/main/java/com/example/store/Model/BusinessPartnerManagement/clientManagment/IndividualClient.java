package com.example.store.Model.BusinessPartnerManagement.clientManagment;

import com.example.store.Model.BusinessPartnerManagement.BusinessPartner;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class IndividualClient extends BusinessPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}