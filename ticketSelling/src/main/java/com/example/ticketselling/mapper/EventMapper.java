package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.EventDto;
import com.example.ticketselling.model.Event;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class EventMapper {

    public Event convertFromDto(EventDto eventDto) {
        if (isNull(eventDto)) {
            return null;
        }

        return new Event(
                eventDto.getId(),
                eventDto.getDescription(),
                eventDto.getName(),
                eventDto.getDuration()
        );
    }

    public EventDto convertToDto(Event event) {
        if (isNull(event)) {
            return null;
        }

        return new EventDto(
                event.getId(),
                event.getDescription(),
                event.getName(),
                event.getDuration()
        );
    }
}
