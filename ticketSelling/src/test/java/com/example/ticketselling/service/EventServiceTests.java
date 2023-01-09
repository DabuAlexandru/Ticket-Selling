package com.example.ticketselling.service;

import com.example.ticketselling.constants.EventConstants;
import com.example.ticketselling.dto.EventDto;
import com.example.ticketselling.mapper.EventMapper;
import com.example.ticketselling.model.BoughtTicket;
import com.example.ticketselling.model.Event;
import com.example.ticketselling.repository.EventRepository;
import com.example.ticketselling.utils.Seeder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {
    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventMapper eventMapper;

    @Test
    @DisplayName("Retrieve all events OK")
    void getAllEvents() {
        // arrange
        List<Event> eventList = List.of(Seeder.event);
        List<EventDto> eventDtoList = List.of(Seeder.eventDto);

        // act
        when(eventRepository.findAll()).thenReturn(eventList);

        when(eventMapper.convertToDto(Seeder.event)).thenReturn(Seeder.eventDto);

        // assert
        List<EventDto> result = eventService.retrieveEvents();
        assertEquals(result, eventDtoList);
    }

    @Test
    @DisplayName("update event OK")
    void updateEvent() {
        // arrange
        int eventId = 1;
        Event event = Seeder.event;
        EventDto editedEventDto = new EventDto(1, event.getDescription(), event.getName(), 999);

        // act
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(eventRepository.save(event)).thenReturn(event);
        when(eventMapper.convertToDto(event)).thenReturn(editedEventDto);

        // assert
        EventDto result = eventService.updateEvent(eventId, editedEventDto);
        assertEquals(result, editedEventDto);
    }

    @Test
    @DisplayName("update event FAIL")
    void updateEventFail() {
        // arrange
        int eventId = 1;
        Event event = null;
        EventDto editedEventDto = Seeder.eventDto;

        // act
        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(event));

        // assert
        RuntimeException eventNotFound = assertThrows(RuntimeException.class, () -> eventService.updateEvent(eventId, editedEventDto));
        assertEquals(eventNotFound.getMessage(), EventConstants.EVENT_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("save event OK")
    void saveEvent()
    {
        // arrange
        Event event = Seeder.event;
        EventDto eventDto = Seeder.eventDto;

        // act
        when(eventMapper.convertFromDto(eventDto)).thenReturn(event);
        when(eventRepository.save(event)).thenReturn(event);
        when(eventMapper.convertToDto(event)).thenReturn(eventDto);

        // assert
        EventDto result = eventService.saveEvent(eventDto);
        assertEquals(result, eventDto);
    }

    @Test
    @DisplayName("delete event OK")
    void deleteEventById() {
        // arrange
        int eventId = 1;
        Event event = Seeder.event;

        // act
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        // assert
        String result = eventService.deleteEventById(eventId);
        assertEquals(result, EventConstants.DELETE_OK_MESSAGE);
    }

    @Test
    @DisplayName("delete event FAIL")
    void deleteEventFail() {
        // arrange
        int eventId = 1;
        Event event = null;

        // act
        when(eventRepository.findById(eventId)).thenReturn(Optional.ofNullable(event));

        // assert
        RuntimeException eventNotFound = assertThrows(RuntimeException.class, () -> eventService.deleteEventById(eventId));
        assertEquals(eventNotFound.getMessage(), EventConstants.EVENT_NOT_FOUND_MESSAGE);
    }
}
