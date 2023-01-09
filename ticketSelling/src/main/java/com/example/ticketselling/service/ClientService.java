package com.example.ticketselling.service;

import com.example.ticketselling.constants.ClientConstants;
import com.example.ticketselling.dto.ClientDto;
import com.example.ticketselling.mapper.ClientMapper;
import com.example.ticketselling.model.Client;
import com.example.ticketselling.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDto> retrieveClients() {
        return clientRepository.findAll()
                .stream().map(clientMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public ClientDto findClientById(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException(ClientConstants.CLIENT_NOT_FOUND_MESSAGE));
        return clientMapper.convertToDto(client);
    }

    public ClientDto updateClient(int clientId, ClientDto updatedClientDto) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException(ClientConstants.CLIENT_NOT_FOUND_MESSAGE));
        if (!isNull(updatedClientDto.getFirstName())) {
            client.setFirstName(updatedClientDto.getFirstName());
        }
        if (!isNull(updatedClientDto.getLastName())) {
            client.setLastName(updatedClientDto.getLastName());
        }
        if (!isNull(updatedClientDto.getEmail())) {
            client.setLastName(updatedClientDto.getEmail());
        }
        if (!isNull(updatedClientDto.getDateOfBirth())) {
            client.setDateOfBirth(updatedClientDto.getDateOfBirth());
        }
        if (!isNull(updatedClientDto.getPhoneNumber())) {
            client.setPhoneNumber(updatedClientDto.getPhoneNumber());
        }
        return clientMapper.convertToDto(clientRepository.save(client));
    }

    public ClientDto saveClient(ClientDto clientDto) {
        return clientMapper.convertToDto(clientRepository.save(clientMapper.convertFromDto(clientDto)));
    }

    public void deleteClientById(Integer clientId) {
        clientRepository.deleteById(clientId);
    }
}
