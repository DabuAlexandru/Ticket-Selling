package com.example.ticketselling.controller;

import com.example.ticketselling.constants.EventPlanningConstants;
import com.example.ticketselling.dto.EventPlanningDto;
import com.example.ticketselling.service.EventPlanningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventPlanning")
public class EventPlanningController {

    private final EventPlanningService eventPlanningService;

    public EventPlanningController(EventPlanningService eventPlanningService) {
        this.eventPlanningService = eventPlanningService;
    }

    @GetMapping
    public ResponseEntity<List<EventPlanningDto>> retrieveEventPlannings() {
        return ResponseEntity.ok().body(eventPlanningService.retrieveEventPlannings());
    }

    @PostMapping("/add")
    public ResponseEntity<EventPlanningDto> addEventPlanning(@RequestBody EventPlanningDto eventPlanningDto) {
        return ResponseEntity.ok().body(eventPlanningService.saveEventPlanning(eventPlanningDto));
    }

    @PutMapping("/edit/{eventPlanningId}")
    public ResponseEntity<EventPlanningDto> editEventPlanning(@PathVariable int eventPlanningId, @RequestBody EventPlanningDto editedEventPlanningDto) {
        return ResponseEntity.ok().body(eventPlanningService.updateEventPlanning(eventPlanningId, editedEventPlanningDto));
    }

    @DeleteMapping("/delete/{eventPlanningId}")
    public ResponseEntity<String> deleteEventPlanning(@PathVariable int eventPlanningId) {
        String message = eventPlanningService.deleteEventPlanningById(eventPlanningId);
        return ResponseEntity.ok().body(message);
    }
}
