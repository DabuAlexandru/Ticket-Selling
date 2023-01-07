package com.example.ticketselling.service;

import com.example.ticketselling.model.EventPlanning;
import com.example.ticketselling.model.TicketType;
import com.example.ticketselling.model.Seat;
import com.example.ticketselling.model.Ticket;
import com.example.ticketselling.repository.EventPlanningRepository;
import com.example.ticketselling.repository.TicketTypeRepository;
import com.example.ticketselling.repository.SeatRepository;
import com.example.ticketselling.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final SeatRepository seatRepository;
    private final EventPlanningRepository eventPlanningRepository;

    public TicketService(TicketRepository ticketRepository,
                         TicketTypeRepository ticketTypeRepository,
                         SeatRepository seatRepository,
                         EventPlanningRepository eventPlanningRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.seatRepository = seatRepository;
        this.eventPlanningRepository = eventPlanningRepository;
    }

    public List<Ticket> retrieveTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(int TicketId) {
        return ticketRepository.findById(TicketId).orElseThrow(() -> new RuntimeException("Ticket not found!"));
    }

    public Ticket updateTicket(int TicketId, Ticket updatedTicket) {
        Ticket ticket = ticketRepository.findById(TicketId).orElseThrow(() -> new RuntimeException("Ticket not found!"));
        if (!isNull(updatedTicket.getTicketType())) {
            TicketType ticketType = ticketTypeRepository.findById(updatedTicket.getTicketType().getId()).orElseThrow(() -> new RuntimeException("TicketType not found!"));
            ticket.setTicketType(ticketType);
        }
        if (!isNull(updatedTicket.getSeat())) {
            Seat seat = seatRepository.findById(updatedTicket.getSeat().getId()).orElseThrow(() -> new RuntimeException("Seat not found!"));
            ticket.setSeat(seat);
        }
        if (!isNull(updatedTicket.getEventPlanning())) {
            EventPlanning eventPlanning = eventPlanningRepository.findById(updatedTicket.getEventPlanning().getId()).orElseThrow(() -> new RuntimeException("EventPlanning not found!"));
            ticket.setEventPlanning(eventPlanning);
        }
        return ticketRepository.save(ticket);
    }

    public Ticket saveTicket(Ticket ticket) {
        TicketType ticketType = ticketTypeRepository.findById(ticket.getTicketType().getId()).orElseThrow(() -> new RuntimeException("TicketType not found!"));
        Seat seat = seatRepository.findById(ticket.getSeat().getId()).orElseThrow(() -> new RuntimeException("Seat not found!"));
        EventPlanning eventPlanning = eventPlanningRepository.findById(ticket.getEventPlanning().getId()).orElseThrow(() -> new RuntimeException("Seat not found!"));

        ticket.setTicketType(ticketType);
        ticket.setSeat(seat);
        ticket.setEventPlanning(eventPlanning);

        return ticketRepository.save(ticket);
    }

    public void deleteTicketById(Integer ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
