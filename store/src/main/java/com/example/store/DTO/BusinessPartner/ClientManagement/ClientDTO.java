package com.example.store.dto.businessPartner.ClientManagement;

import com.example.store.dto.businessPartner.BusinessPartnerDTO;
import lombok.Data;


@Data
public class ClientDTO extends BusinessPartnerDTO {

    protected String firstName;
    protected String lastName;
}
