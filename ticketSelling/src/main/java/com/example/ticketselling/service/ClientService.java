package com.example.ticketselling.service;

import com.example.ticketselling.model.Client;
import com.example.ticketselling.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> retrieveClients() {
        return clientRepository.findAll();
    }

    public Client findClientById(int clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found!"));
    }

    public Client updateClient(int clientId, Client updatedClient) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found!"));
        if(!isNull(updatedClient.getFirstName())) {
            client.setFirstName(updatedClient.getFirstName());
        }
        if(!isNull(updatedClient.getLastName())) {
            client.setLastName(updatedClient.getLastName());
        }
        if(!isNull(updatedClient.getEmail())) {
            client.setLastName(updatedClient.getEmail());
        }
        if(!isNull(updatedClient.getDateOfBirth())) {
            client.setDateOfBirth(updatedClient.getDateOfBirth());
        }
        if(!isNull(updatedClient.getPhoneNumber())) {
            client.setPhoneNumber(updatedClient.getPhoneNumber());
        }
        return clientRepository.save(client);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClientById(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
