package com.example.store.Service.BusinessPartnerManagement.clientManagement;

import com.example.store.DTO.BusinessPartner.ClientManagement.ClientDTO;
import com.example.store.DTO.stockManagment.request.CodeRequest;
import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;
import com.example.store.Model.StockMangement.ProductVariant;

import java.util.List;

public interface ClientService {

    Client saveClient(ClientDTO client);
    Client findClientById(Long clientId);
    List<Client> fetchClientList();
    void deleteClientById(Long clientId);
    Client updateClient(ClientDTO client , Long clientId);
     List<Client> findByFirstNameStartingWithIgnoreCase(String firstName);
}
