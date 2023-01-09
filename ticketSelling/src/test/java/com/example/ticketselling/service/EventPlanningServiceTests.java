package com.example.ticketselling.service;

import com.example.ticketselling.constants.EventPlanningConstants;
import com.example.ticketselling.dto.EventPlanningDto;
import com.example.ticketselling.mapper.EventPlanningMapper;
import com.example.ticketselling.model.Event;
import com.example.ticketselling.model.EventPlanning;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.repository.EventPlanningRepository;
import com.example.ticketselling.repository.EventRepository;
import com.example.ticketselling.repository.LocationRepository;
import com.example.ticketselling.utils.Seeder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventPlanningServiceTests {
    @InjectMocks
    private EventPlanningService eventPlanningService;

    @Mock
    private EventPlanningRepository eventPlanningRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private LocationRepository locationRepository;
    @Mock
    private EventPlanningMapper eventPlanningMapper;

    @Test
    @DisplayName("Retrieve all eventPlannings OK")
    void getAllEventPlannings() {
        // arrange
        List<EventPlanning> eventPlanningList = List.of(Seeder.eventPlanning);
        List<EventPlanningDto> eventPlanningDtoList = List.of(Seeder.eventPlanningDto);

        // act
        when(eventPlanningRepository.findAll()).thenReturn(eventPlanningList);

        when(eventPlanningMapper.convertToDto(Seeder.eventPlanning)).thenReturn(Seeder.eventPlanningDto);

        // assert
        List<EventPlanningDto> result = eventPlanningService.retrieveEventPlannings();
        assertEquals(result, eventPlanningDtoList);
    }

    @Test
    @DisplayName("update eventPlanning OK")
    void updateEventPlanning() {
        // arrange
        int eventPlanningId = 1;
        EventPlanning eventPlanning = Seeder.eventPlanning;
        EventPlanningDto editedEventPlanningDto = new EventPlanningDto(1, Seeder.eventPlanningDto.getLocation(), Seeder.eventPlanningDto.getEvent(), new Date());

        // act
        when(eventPlanningRepository.findById(eventPlanningId)).thenReturn(Optional.of(eventPlanning));
        when(eventPlanningRepository.save(eventPlanning)).thenReturn(eventPlanning);
        when(eventPlanningMapper.convertToDto(eventPlanning)).thenReturn(editedEventPlanningDto);

        Event event = eventPlanning.getEvent();
        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

        Location location = eventPlanning.getLocation();
        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        // assert
        EventPlanningDto result = eventPlanningService.updateEventPlanning(eventPlanningId, editedEventPlanningDto);
        assertEquals(result, editedEventPlanningDto);
    }

    @Test
    @DisplayName("update eventPlanning FAIL")
    void updateEventPlanningFail() {
        // arrange
        int eventPlanningId = 1;
        EventPlanning eventPlanning = null;
        EventPlanningDto editedEventPlanningDto = Seeder.eventPlanningDto;

        // act
        when(eventPlanningRepository.findById(eventPlanningId)).thenReturn(Optional.ofNullable(eventPlanning));

        // assert
        RuntimeException eventPlanningNotFound = assertThrows(RuntimeException.class, () -> eventPlanningService.updateEventPlanning(eventPlanningId, editedEventPlanningDto));
        assertEquals(eventPlanningNotFound.getMessage(), EventPlanningConstants.EVENT_PLANNING_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("save eventPlanning OK")
    void saveEventPlanning()
    {
        // arrange
        EventPlanning eventPlanning = Seeder.eventPlanning;
        EventPlanningDto eventPlanningDto = Seeder.eventPlanningDto;

        // act
        when(eventPlanningMapper.convertFromDto(eventPlanningDto)).thenReturn(eventPlanning);
        when(eventPlanningRepository.save(eventPlanning)).thenReturn(eventPlanning);
        when(eventPlanningMapper.convertToDto(eventPlanning)).thenReturn(eventPlanningDto);

        Event event = eventPlanning.getEvent();
        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));

        Location location = eventPlanning.getLocation();
        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        // assert
        EventPlanningDto result = eventPlanningService.saveEventPlanning(eventPlanningDto);
        assertEquals(result, eventPlanningDto);
    }

    @Test
    @DisplayName("delete eventPlanning OK")
    void deleteEventPlanningById() {
        // arrange
        int eventPlanningId = 1;
        EventPlanning eventPlanning = Seeder.eventPlanning;

        // act
        when(eventPlanningRepository.findById(eventPlanningId)).thenReturn(Optional.of(eventPlanning));

        // assert
        String result = eventPlanningService.deleteEventPlanningById(eventPlanningId);
        assertEquals(result, EventPlanningConstants.DELETE_OK_MESSAGE);
    }

    @Test
    @DisplayName("delete eventPlanning FAIL")
    void deleteEventPlanningFail() {
        // arrange
        int eventPlanningId = 1;
        EventPlanning eventPlanning = null;

        // act
        when(eventPlanningRepository.findById(eventPlanningId)).thenReturn(Optional.ofNullable(eventPlanning));

        // assert
        RuntimeException eventPlanningNotFound = assertThrows(RuntimeException.class, () -> eventPlanningService.deleteEventPlanningById(eventPlanningId));
        assertEquals(eventPlanningNotFound.getMessage(), EventPlanningConstants.EVENT_PLANNING_NOT_FOUND_MESSAGE);
    }
}
