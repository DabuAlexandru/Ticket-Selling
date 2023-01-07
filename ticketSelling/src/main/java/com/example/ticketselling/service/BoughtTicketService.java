package com.example.ticketselling.service;

import com.example.ticketselling.model.Client;
import com.example.ticketselling.model.Ticket;
import com.example.ticketselling.model.BoughtTicket;
import com.example.ticketselling.repository.ClientRepository;
import com.example.ticketselling.repository.TicketRepository;
import com.example.ticketselling.repository.BoughtTicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class BoughtTicketService {
    private final BoughtTicketRepository boughtTicketRepository;
    private final ClientRepository clientRepository;
    private final TicketRepository ticketRepository;

    public BoughtTicketService(BoughtTicketRepository boughtTicketRepository,
                                ClientRepository clientRepository,
                                TicketRepository ticketRepository) {
        this.boughtTicketRepository = boughtTicketRepository;
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<BoughtTicket> retrieveBoughtTickets() {
        return boughtTicketRepository.findAll();
    }

    public BoughtTicket findBoughtTicketById(int BoughtTicketId) {
        return boughtTicketRepository.findById(BoughtTicketId).orElseThrow(() -> new RuntimeException("BoughtTicket not found!"));
    }

    public BoughtTicket updateBoughtTicket(int BoughtTicketId, BoughtTicket updatedBoughtTicket) {
        BoughtTicket boughtTicket = boughtTicketRepository.findById(BoughtTicketId).orElseThrow(() -> new RuntimeException("BoughtTicket not found!"));
        if (!isNull(updatedBoughtTicket.getBoughtAt())) {
            boughtTicket.setBoughtAt(updatedBoughtTicket.getBoughtAt());
        }
        if (!isNull(updatedBoughtTicket.getClient())) {
            Client client = clientRepository.findById(updatedBoughtTicket.getClient().getId()).orElseThrow(() -> new RuntimeException("Client not found!"));
            boughtTicket.setClient(client);
        }
        if (!isNull(updatedBoughtTicket.getTicket())) {
            Ticket ticket = ticketRepository.findById(updatedBoughtTicket.getTicket().getId()).orElseThrow(() -> new RuntimeException("Ticket not found!"));
            boughtTicket.setTicket(ticket);
        }
        return boughtTicketRepository.save(boughtTicket);
    }

    public BoughtTicket saveBoughtTicket(BoughtTicket boughtTicket) {
        Client client = clientRepository.findById(boughtTicket.getClient().getId()).orElseThrow(() -> new RuntimeException("Client not found!"));
        Ticket ticket = ticketRepository.findById(boughtTicket.getTicket().getId()).orElseThrow(() -> new RuntimeException("Ticket not found!"));

        boughtTicket.setClient(client);
        boughtTicket.setTicket(ticket);

        return boughtTicketRepository.save(boughtTicket);
    }

    public void deleteBoughtTicketById(Integer boughtTicketId) {
        boughtTicketRepository.deleteById(boughtTicketId);
    }
}
