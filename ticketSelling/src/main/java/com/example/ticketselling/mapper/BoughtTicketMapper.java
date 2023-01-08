package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.BoughtTicketDto;
import com.example.ticketselling.model.BoughtTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class BoughtTicketMapper {

    private final ClientMapper clientMapper;
    private final TicketMapper ticketMapper;

    @Autowired
    public BoughtTicketMapper(ClientMapper clientMapper, TicketMapper ticketMapper) {
        this.clientMapper = clientMapper;
        this.ticketMapper = ticketMapper;
    }

    public BoughtTicket convertFromDto(BoughtTicketDto boughtTicketDto) {
        if (isNull(boughtTicketDto)) {
            return null;
        }

        return new BoughtTicket(
                boughtTicketDto.getId(),
                ticketMapper.convertFromDto(boughtTicketDto.getTicket()),
                clientMapper.convertFromDto(boughtTicketDto.getClient()),
                boughtTicketDto.getBoughtAt()
        );
    }

    public BoughtTicketDto convertToDto(BoughtTicket boughtTicket) {
        if (isNull(boughtTicket)) {
            return null;
        }

        return new BoughtTicketDto(
                boughtTicket.getId(),
                ticketMapper.convertToDto(boughtTicket.getTicket()),
                clientMapper.convertToDto(boughtTicket.getClient()),
                boughtTicket.getBoughtAt()
        );
    }
}
