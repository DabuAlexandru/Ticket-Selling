package com.example.ticketselling.service;

import com.example.ticketselling.model.Event;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.EventPlanning;
import com.example.ticketselling.repository.EventRepository;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.repository.EventPlanningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class EventPlanningService {
    private final EventPlanningRepository eventPlanningRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventPlanningService(EventPlanningRepository eventPlanningRepository,
                             EventRepository eventRepository,
                                LocationRepository locationRepository) {
        this.eventPlanningRepository = eventPlanningRepository;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    public List<EventPlanning> retrieveEventPlannings() {
        return eventPlanningRepository.findAll();
    }

    public EventPlanning findEventPlanningById(int EventPlanningId) {
        return eventPlanningRepository.findById(EventPlanningId).orElseThrow(() -> new RuntimeException("EventPlanning not found!"));
    }

    public EventPlanning updateEventPlanning(int EventPlanningId, EventPlanning updatedEventPlanning) {
        EventPlanning eventPlanning = eventPlanningRepository.findById(EventPlanningId).orElseThrow(() -> new RuntimeException("EventPlanning not found!"));
        if (!isNull(updatedEventPlanning.getStartDate())) {
            eventPlanning.setStartDate(updatedEventPlanning.getStartDate());
        }
        if (!isNull(updatedEventPlanning.getEvent())) {
            Event event = eventRepository.findById(updatedEventPlanning.getEvent().getId()).orElseThrow(() -> new RuntimeException("Event not found!"));
            eventPlanning.setEvent(event);
        }
        if (!isNull(updatedEventPlanning.getLocation())) {
            Location location = locationRepository.findById(updatedEventPlanning.getLocation().getId()).orElseThrow(() -> new RuntimeException("Location not found!"));
            eventPlanning.setLocation(location);
        }
        return eventPlanningRepository.save(eventPlanning);
    }

    public EventPlanning saveEventPlanning(EventPlanning eventPlanning) {
        Event event = eventRepository.findById(eventPlanning.getEvent().getId()).orElseThrow(() -> new RuntimeException("Event not found!"));
        Location location = locationRepository.findById(eventPlanning.getLocation().getId()).orElseThrow(() -> new RuntimeException("Location not found!"));

        eventPlanning.setEvent(event);
        eventPlanning.setLocation(location);

        return eventPlanningRepository.save(eventPlanning);
    }

    public void deleteEventPlanningById(Integer eventPlanningId) {
        eventPlanningRepository.deleteById(eventPlanningId);
    }
}
