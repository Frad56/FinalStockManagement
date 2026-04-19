package com.example.store.DTO.supplierManagement;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierDTO {
    private String companyName;
    private String contactName;
    private String phoneNumber ;
    private String fax;
    private String email;
    private String address;
    private String city;
    private String postal_code;
    private String country;


}
