package com.example.ticketselling.controller;

import com.example.ticketselling.constants.EventConstants;
import com.example.ticketselling.dto.EventDto;
import com.example.ticketselling.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDto>> retrieveEvents() {
        return ResponseEntity.ok().body(eventService.retrieveEvents());
    }

    @PostMapping("/add")
    public ResponseEntity<EventDto> addEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok().body(eventService.saveEvent(eventDto));
    }

    @PutMapping("/edit/{eventId}")
    public ResponseEntity<EventDto> editEvent(@PathVariable int eventId, @RequestBody EventDto editedEventDto) {
        return ResponseEntity.ok().body(eventService.updateEvent(eventId, editedEventDto));
    }

    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable int eventId) {
        eventService.deleteEventById(eventId);
        return ResponseEntity.ok().body(EventConstants.DELETED_OK_MESSAGE);
    }
}
