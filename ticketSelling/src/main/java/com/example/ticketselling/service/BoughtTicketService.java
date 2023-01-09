package com.example.ticketselling.service;

import com.example.ticketselling.constants.BoughtTicketConstants;
import com.example.ticketselling.dto.BoughtTicketDto;
import com.example.ticketselling.mapper.BoughtTicketMapper;
import com.example.ticketselling.model.Client;
import com.example.ticketselling.model.Ticket;
import com.example.ticketselling.model.BoughtTicket;
import com.example.ticketselling.repository.ClientRepository;
import com.example.ticketselling.repository.TicketRepository;
import com.example.ticketselling.repository.BoughtTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class BoughtTicketService {
    private final BoughtTicketRepository boughtTicketRepository;
    private final ClientRepository clientRepository;
    private final TicketRepository ticketRepository;

    private final BoughtTicketMapper boughtTicketMapper;

    public BoughtTicketService(BoughtTicketRepository boughtTicketRepository,
                               ClientRepository clientRepository,
                               TicketRepository ticketRepository,
                               BoughtTicketMapper boughtTicketMapper) {
        this.boughtTicketRepository = boughtTicketRepository;
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
        this.boughtTicketMapper = boughtTicketMapper;
    }

    public List<BoughtTicketDto> retrieveBoughtTickets() {
        return boughtTicketRepository.findAll()
                .stream().map(boughtTicketMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public BoughtTicketDto findBoughtTicketById(int BoughtTicketId) {
        BoughtTicket boughtTicket =  boughtTicketRepository.findById(BoughtTicketId).orElseThrow(() -> new RuntimeException(BoughtTicketConstants.BOUGHT_TICKET_NOT_FOUND_MESSAGE));
        return boughtTicketMapper.convertToDto(boughtTicket);
    }

    public BoughtTicketDto updateBoughtTicket(int BoughtTicketId, BoughtTicketDto updatedBoughtTicketDto) {
        BoughtTicket boughtTicket = boughtTicketRepository.findById(BoughtTicketId).orElseThrow(() -> new RuntimeException(BoughtTicketConstants.BOUGHT_TICKET_NOT_FOUND_MESSAGE));
        if (!isNull(updatedBoughtTicketDto.getBoughtAt())) {
            boughtTicket.setBoughtAt(updatedBoughtTicketDto.getBoughtAt());
        }

        return getInjectedBoughtTicket(updatedBoughtTicketDto, boughtTicket);
    }

    public BoughtTicketDto getInjectedBoughtTicket(BoughtTicketDto boughtTicketDto, BoughtTicket boughtTicket) {
        if (!isNull(boughtTicketDto.getClient())) {
            Client client = clientRepository.findById(boughtTicketDto.getClient().getId()).orElseThrow(() -> new RuntimeException("Client not found!"));
            boughtTicket.setClient(client);
        }
        if (!isNull(boughtTicketDto.getTicket())) {
            Ticket ticket = ticketRepository.findById(boughtTicketDto.getTicket().getId()).orElseThrow(() -> new RuntimeException("Ticket not found!"));
            boughtTicket.setTicket(ticket);
        }
        return boughtTicketMapper.convertToDto(boughtTicketRepository.save(boughtTicket));
    }

    public BoughtTicketDto saveBoughtTicket(BoughtTicketDto boughtTicketDto) {
        BoughtTicket boughtTicket = boughtTicketMapper.convertFromDto(boughtTicketDto);
        return getInjectedBoughtTicket(boughtTicketDto, boughtTicket);
    }

    public void deleteBoughtTicketById(Integer boughtTicketId) {
        boughtTicketRepository.deleteById(boughtTicketId);
    }
}
