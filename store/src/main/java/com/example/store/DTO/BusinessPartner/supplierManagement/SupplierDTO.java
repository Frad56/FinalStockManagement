package com.example.store.DTO.BusinessPartner.supplierManagement;


import com.example.store.DTO.BusinessPartner.BusinessPartnerDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class SupplierDTO extends BusinessPartnerDTO {

    protected String companyName;

    protected String contactName;
}
