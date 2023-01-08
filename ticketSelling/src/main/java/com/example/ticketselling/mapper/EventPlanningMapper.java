package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.EventPlanningDto;
import com.example.ticketselling.model.EventPlanning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class EventPlanningMapper {

    private final EventMapper eventMapper;
    private final LocationMapper locationMapper;

    @Autowired
    public EventPlanningMapper(EventMapper eventMapper, LocationMapper locationMapper) {
        this.eventMapper = eventMapper;
        this.locationMapper = locationMapper;
    }

    public EventPlanning convertFromDto(EventPlanningDto eventPlanningDto) {
        if (isNull(eventPlanningDto)) {
            return null;
        }

        return new EventPlanning(
                eventPlanningDto.getId(),
                locationMapper.convertFromDto(eventPlanningDto.getLocation()),
                eventMapper.convertFromDto(eventPlanningDto.getEvent()),
                eventPlanningDto.getStartDate()
        );
    }

    public EventPlanningDto convertToDto(EventPlanning eventPlanning) {
        if (isNull(eventPlanning)) {
            return null;
        }

        return new EventPlanningDto(
                eventPlanning.getId(),
                locationMapper.convertToDto(eventPlanning.getLocation()),
                eventMapper.convertToDto(eventPlanning.getEvent()),
                eventPlanning.getStartDate()
        );
    }
}
