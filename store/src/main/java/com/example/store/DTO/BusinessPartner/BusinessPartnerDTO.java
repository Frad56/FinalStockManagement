package com.example.store.dto.BusinessPartner;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessPartnerDTO {
    private String phoneNumber ;
    private String fax;
    private String email;
    private String address;
    private String city;
    private String postalCode;
    private String country;
}
