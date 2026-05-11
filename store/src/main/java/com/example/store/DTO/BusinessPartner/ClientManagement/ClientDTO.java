package com.example.store.DTO.BusinessPartner.ClientManagement;

import com.example.store.DTO.BusinessPartner.BusinessPartnerDTO;
import jakarta.persistence.Column;
import lombok.Data;


@Data
public class ClientDTO extends BusinessPartnerDTO {

    protected String firstName;
    protected String lastName;
}
