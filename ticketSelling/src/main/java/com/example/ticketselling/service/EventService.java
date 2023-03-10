package com.example.ticketselling.service;

import com.example.ticketselling.constants.ClientConstants;
import com.example.ticketselling.constants.EventConstants;
import com.example.ticketselling.dto.EventDto;
import com.example.ticketselling.mapper.EventMapper;
import com.example.ticketselling.model.Client;
import com.example.ticketselling.model.Event;
import com.example.ticketselling.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<EventDto> retrieveEvents() {
        return eventRepository.findAll()
                .stream().map(eventMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public EventDto findEventById(int eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException(EventConstants.EVENT_NOT_FOUND_MESSAGE));
        return eventMapper.convertToDto(event);
    }

    public EventDto updateEvent(int eventId, EventDto updatedEventDto) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException(EventConstants.EVENT_NOT_FOUND_MESSAGE));
        if (!isNull(updatedEventDto.getName())) {
            event.setName(updatedEventDto.getName());
        }
        if (!isNull(updatedEventDto.getDescription())) {
            event.setDescription(updatedEventDto.getDescription());
        }
        if (updatedEventDto.getDuration() != 0) {
            event.setDuration(updatedEventDto.getDuration());
        }
        return eventMapper.convertToDto(eventRepository.save(event));
    }

    public EventDto saveEvent(EventDto eventDto) {
        return eventMapper.convertToDto(eventRepository.save(eventMapper.convertFromDto(eventDto)));
    }

    public String deleteEventById(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException(EventConstants.EVENT_NOT_FOUND_MESSAGE));
        eventRepository.deleteById(event.getId());
        return EventConstants.DELETE_OK_MESSAGE;
    }
}
