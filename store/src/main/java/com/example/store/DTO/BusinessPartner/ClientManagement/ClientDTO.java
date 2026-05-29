package com.example.store.dto.BusinessPartner.ClientManagement;

import com.example.store.dto.BusinessPartner.BusinessPartnerDTO;
import lombok.Data;


@Data
public class ClientDTO extends BusinessPartnerDTO {

    protected String firstName;
    protected String lastName;
}
