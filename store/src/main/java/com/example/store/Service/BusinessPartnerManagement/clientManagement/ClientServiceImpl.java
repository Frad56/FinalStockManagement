package com.example.store.Service.BusinessPartnerManagement.clientManagement;

import com.example.store.DTO.BusinessPartner.ClientManagement.ClientDTO;
import com.example.store.Exception.ElementNotFoundException;
import com.example.store.Model.BusinessPartnerManagement.clientManagment.Client;
import com.example.store.Repository.BusinessPartner.ClientManagement.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements  ClientService{

    private ClientRepository clientRepository;

    public  ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private void mapClientData(ClientDTO existingClient, Client client) {
        client.setFirstName(existingClient.getFirstName());
        client.setLastName(existingClient.getLastName());
        client.setPhoneNumber(existingClient.getPhoneNumber());
        client.setFax(existingClient.getFax());
        client.setEmail(existingClient.getEmail());
        client.setAddress(existingClient.getAddress());
        client.setCity(existingClient.getCity());
        client.setPostalCode(existingClient.getPostalCode());
        client.setCountry(existingClient.getCountry());
    }

    @Override
    public Client saveClient(ClientDTO clientDTO) {
        Client client = new Client();
        mapClientData(clientDTO, client);
        return clientRepository.save(client);
    }
    @Override
    public Client findClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ElementNotFoundException(clientId));
    }

    @Override
    public  List<Client> findByFirstNameStartingWithIgnoreCase(String firstName){
        return clientRepository.findByFirstNameStartingWithIgnoreCase(firstName);
    }

    @Override
    public List<Client> fetchClientList() {
        return clientRepository.findAll();
    }
    @Override
    public void deleteClientById(Long clientId) {
        clientRepository.deleteById(clientId);
    }
    @Override
    public Client updateClient(ClientDTO clientDTO, Long clientId) {
        Client existingClient = findClientById(clientId);
        mapClientData(clientDTO, existingClient);
        return clientRepository.save(existingClient);
    }
}

