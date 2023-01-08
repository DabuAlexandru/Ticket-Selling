package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.ClientDto;
import com.example.ticketselling.model.Client;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ClientMapper {

    public Client convertFromDto(ClientDto clientDto) {
        if (isNull(clientDto)) {
            return null;
        }

        return new Client(
                clientDto.getId(),
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail(),
                clientDto.getPhoneNumber(),
                clientDto.getDateOfBirth()
        );
    }

    public ClientDto convertToDto(Client client) {
        if (isNull(client)) {
            return null;
        }

        return new ClientDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getDateOfBirth()
        );
    }

}
