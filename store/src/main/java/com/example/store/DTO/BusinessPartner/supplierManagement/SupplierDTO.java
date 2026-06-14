package com.example.store.dto.businessPartner.supplierManagement;


import com.example.store.dto.businessPartner.BusinessPartnerDTO;
import lombok.Data;


@Data
public class SupplierDTO extends BusinessPartnerDTO {

    protected String companyName;

    protected String contactName;

    protected  String taxIdentificationNumber;
}
