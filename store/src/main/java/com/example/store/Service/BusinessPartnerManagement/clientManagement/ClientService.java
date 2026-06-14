package com.example.store.service.BusinessPartnerManagement.clientManagement;

import com.example.store.dto.businessPartner.ClientManagement.ClientDTO;
import com.example.store.model.businessPartnerManagement.clientManagment.Client;

import java.util.List;

public interface ClientService {

    Client saveClient(ClientDTO client);
    Client findClientById(Long clientId);
    List<Client> fetchClientList();
    void deleteClientById(Long clientId);
    Client updateClient(ClientDTO client , Long clientId);
     List<Client> findByFirstNameStartingWithIgnoreCase(String firstName);
}
