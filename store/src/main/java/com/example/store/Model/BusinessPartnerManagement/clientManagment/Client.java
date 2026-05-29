package com.example.store.model.businessPartnerManagement.clientManagment;

import com.example.store.model.businessPartnerManagement.BusinessPartner;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "client")
@Data
public class Client extends BusinessPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

}
