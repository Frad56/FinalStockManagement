package com.example.store.dto.BusinessPartner.supplierManagement;


import com.example.store.dto.BusinessPartner.BusinessPartnerDTO;
import lombok.Data;


@Data
public class SupplierDTO extends BusinessPartnerDTO {

    protected String companyName;

    protected String contactName;

    protected  String taxIdentificationNumber;
}
