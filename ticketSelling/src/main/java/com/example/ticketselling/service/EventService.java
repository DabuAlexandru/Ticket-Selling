package com.example.ticketselling.service;

import com.example.ticketselling.model.Event;
import com.example.ticketselling.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> retrieveEvents() {
        return eventRepository.findAll();
    }

    public Event findEventById(int eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found!"));
    }

    public Event updateEvent(int eventId, Event updatedEvent) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found!"));
        if(!isNull(updatedEvent.getName())) {
            event.setName(updatedEvent.getName());
        }
        if(!isNull(updatedEvent.getDescription())) {
            event.setDescription(updatedEvent.getDescription());
        }
        if(updatedEvent.getDuration() != 0) {
            event.setDuration(updatedEvent.getDuration());
        }
        return eventRepository.save(event);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEventById(Integer eventId) {
        eventRepository.deleteById(eventId);
    }
}
