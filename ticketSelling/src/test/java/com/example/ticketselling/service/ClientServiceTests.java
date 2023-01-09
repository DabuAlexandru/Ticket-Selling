package com.example.ticketselling.service;

import com.example.ticketselling.constants.ClientConstants;
import com.example.ticketselling.dto.ClientDto;
import com.example.ticketselling.mapper.ClientMapper;
import com.example.ticketselling.model.BoughtTicket;
import com.example.ticketselling.model.Client;
import com.example.ticketselling.repository.ClientRepository;
import com.example.ticketselling.utils.Seeder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Test
    @DisplayName("Retrieve all clients OK")
    void getAllClients() {
        // arrange
        List<Client> clientList = List.of(Seeder.client);
        List<ClientDto> clientDtoList = List.of(Seeder.clientDto);

        // act
        when(clientRepository.findAll()).thenReturn(clientList);

        when(clientMapper.convertToDto(Seeder.client)).thenReturn(Seeder.clientDto);

        // assert
        List<ClientDto> result = clientService.retrieveClients();
        assertEquals(result, clientDtoList);
    }

    @Test
    @DisplayName("update client OK")
    void updateClient() {
        // arrange
        int clientId = 1;
        Client client = Seeder.client;
        ClientDto editedClientDto = new ClientDto(1, client.getFirstName(), client.getLastName(), client.getEmail(), "1111", client.getDateOfBirth());

        // act
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.convertToDto(client)).thenReturn(editedClientDto);

        // assert
        ClientDto result = clientService.updateClient(clientId, editedClientDto);
        assertEquals(result, editedClientDto);
    }

    @Test
    @DisplayName("update client FAIL")
    void updateClientFail() {
        // arrange
        int clientId = 1;
        Client client = null;
        ClientDto editedClientDto = Seeder.clientDto;

        // act
        when(clientRepository.findById(clientId)).thenReturn(Optional.ofNullable(client));

        // assert
        RuntimeException clientNotFound = assertThrows(RuntimeException.class, () -> clientService.updateClient(clientId, editedClientDto));
        assertEquals(clientNotFound.getMessage(), ClientConstants.CLIENT_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("save client OK")
    void saveClient()
    {
        // arrange
        Client client = Seeder.client;
        ClientDto clientDto = Seeder.clientDto;

        // act
        when(clientMapper.convertFromDto(clientDto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.convertToDto(client)).thenReturn(clientDto);

        // assert
        ClientDto result = clientService.saveClient(clientDto);
        assertEquals(result, clientDto);
    }

    @Test
    @DisplayName("delete client OK")
    void deleteClientById() {
        // arrange
        int clientId = 1;
        Client client = Seeder.client;

        // act
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // assert
        String result = clientService.deleteClientById(clientId);
        assertEquals(result, ClientConstants.DELETE_OK_MESSAGE);
    }

    @Test
    @DisplayName("delete client FAIL")
    void deleteClientFail() {
        // arrange
        int clientId = 1;
        Client client = null;

        // act
        when(clientRepository.findById(clientId)).thenReturn(Optional.ofNullable(client));

        // assert
        RuntimeException clientNotFound = assertThrows(RuntimeException.class, () -> clientService.deleteClientById(clientId));
        assertEquals(clientNotFound.getMessage(), ClientConstants.CLIENT_NOT_FOUND_MESSAGE);
    }
}
