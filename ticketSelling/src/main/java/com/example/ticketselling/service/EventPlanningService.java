package com.example.ticketselling.service;

import com.example.ticketselling.constants.EventConstants;
import com.example.ticketselling.constants.EventPlanningConstants;
import com.example.ticketselling.constants.LocationConstants;
import com.example.ticketselling.dto.EventPlanningDto;
import com.example.ticketselling.mapper.EventPlanningMapper;
import com.example.ticketselling.model.Event;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.EventPlanning;
import com.example.ticketselling.repository.EventRepository;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.repository.EventPlanningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class EventPlanningService {
    private final EventPlanningRepository eventPlanningRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    private final EventPlanningMapper eventPlanningMapper;

    public EventPlanningService(EventPlanningRepository eventPlanningRepository,
                                EventRepository eventRepository,
                                LocationRepository locationRepository,
                                EventPlanningMapper eventPlanningMapper) {
        this.eventPlanningRepository = eventPlanningRepository;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.eventPlanningMapper = eventPlanningMapper;
    }

    public List<EventPlanningDto> retrieveEventPlannings() {
        return eventPlanningRepository.findAll()
                .stream().map(eventPlanningMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public EventPlanningDto findEventPlanningById(int EventPlanningId) {
        EventPlanning eventPlanning = eventPlanningRepository.findById(EventPlanningId).orElseThrow(() -> new RuntimeException(EventPlanningConstants.EVENT_PLANNING_NOT_FOUND_MESSAGE));
        return eventPlanningMapper.convertToDto(eventPlanning);
    }

    public EventPlanningDto updateEventPlanning(int EventPlanningId, EventPlanningDto updatedEventPlanningDto) {
        EventPlanning eventPlanning = eventPlanningRepository.findById(EventPlanningId).orElseThrow(() -> new RuntimeException(EventPlanningConstants.EVENT_PLANNING_NOT_FOUND_MESSAGE));
        if (!isNull(updatedEventPlanningDto.getStartDate())) {
            eventPlanning.setStartDate(updatedEventPlanningDto.getStartDate());
        }
        return getEventPlanningDtoWithInjectedObjects(updatedEventPlanningDto, eventPlanning);
    }

    private EventPlanningDto getEventPlanningDtoWithInjectedObjects(EventPlanningDto eventPlanningDto, EventPlanning eventPlanning) {
        if (!isNull(eventPlanningDto.getEvent())) {
            Event event = eventRepository.findById(eventPlanningDto.getEvent().getId()).orElseThrow(() -> new RuntimeException(EventConstants.EVENT_NOT_FOUND_MESSAGE));
            eventPlanning.setEvent(event);
        }
        if (!isNull(eventPlanningDto.getLocation())) {
            Location location = locationRepository.findById(eventPlanningDto.getLocation().getId()).orElseThrow(() -> new RuntimeException(LocationConstants.LOCATION_NOT_FOUND_MESSAGE));
            eventPlanning.setLocation(location);
        }
        return eventPlanningMapper.convertToDto(eventPlanningRepository.save(eventPlanning));
    }

    public EventPlanningDto saveEventPlanning(EventPlanningDto eventPlanningDto) {
        EventPlanning eventPlanning = eventPlanningMapper.convertFromDto(eventPlanningDto);
        return getEventPlanningDtoWithInjectedObjects(eventPlanningDto, eventPlanning);
    }

    public void deleteEventPlanningById(Integer eventPlanningId) {
        eventPlanningRepository.deleteById(eventPlanningId);
    }
}
