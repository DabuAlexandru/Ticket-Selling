package com.example.ticketselling.service;

import com.example.ticketselling.model.BoughtTicket;
import com.example.ticketselling.repository.BoughtTicketRepository;
import com.example.ticketselling.repository.ClientRepository;
import com.example.ticketselling.repository.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BoughtTicketServiceTests {
    @InjectMocks
    private BoughtTicketService boughtTicketService;

    @Mock
    private BoughtTicketRepository boughtTicketRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Test
    @DisplayName("Retrieve all bought tickets OK")
    void getAllBoughtTickets() {
        // arrange
        List<BoughtTicket> boughtTicketList = new ArrayList<>();
        // act
        boughtTicketService.retrieveBoughtTickets();
        // assert
    }

}
