package com.example.ticketselling.utils;

import com.example.ticketselling.dto.ClientDto;
import com.example.ticketselling.dto.EventDto;
import com.example.ticketselling.dto.EventPlanningDto;
import com.example.ticketselling.dto.LocationDto;
import com.example.ticketselling.model.Client;
import com.example.ticketselling.model.Event;
import com.example.ticketselling.model.EventPlanning;
import com.example.ticketselling.model.Location;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Seeder {

    public static Location location = new Location(1, "location1", "someDetails", "location1", 45);
    public static LocationDto locationDto = new LocationDto(1, "location1", "someDetails", "location1", 45);

    public static Client client = new Client(1, "firstName", "lastName", "email@email.com", "0000", new Date());
    public static ClientDto clientDto = new ClientDto(1, "firstName", "lastName", "email@email.com", "0000", new Date());

    public static Event event = new Event(1, "desc", "name", 55);
    public static EventDto eventDto = new EventDto(1, "desc", "name", 55);

    public static EventPlanning eventPlanning = new EventPlanning(1, location, event,  new Date());
    public static EventPlanningDto eventPlanningDto = new EventPlanningDto(1, locationDto, eventDto,  new Date());
}
