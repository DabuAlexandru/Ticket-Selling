package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.TicketTypeDto;
import com.example.ticketselling.model.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TicketTypeMapper {

    private final EventMapper eventMapper;

    @Autowired
    public TicketTypeMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public TicketType convertFromDto(TicketTypeDto ticketTypeDto) {
        if(isNull(ticketTypeDto)) {
            return null;
        }

        return new TicketType(
                ticketTypeDto.getId(),
                ticketTypeDto.getTitle(),
                ticketTypeDto.getPrice(),
                eventMapper.convertFromDto(ticketTypeDto.getEvent())
        );
    }

    public TicketTypeDto convertToDto(TicketType ticketType) {
        if(isNull(ticketType)) {
            return null;
        }

        return new TicketTypeDto(
                ticketType.getId(),
                ticketType.getTitle(),
                ticketType.getPrice(),
                eventMapper.convertToDto(ticketType.getEvent())
        );
    }
}
