package com.example.ticketselling.mapper;

import com.example.ticketselling.dto.LocationDto;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.model.Seat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class LocationMapper {

    public Location convertFromDto(LocationDto locationDto) {
        if(isNull(locationDto)) {
            return null;
        }

        return new Location(
                locationDto.getId(),
                locationDto.getAddress(),
                locationDto.getAddressDetails(),
                locationDto.getName(),
                locationDto.getMaxCapacity()
        );
    }

    public LocationDto convertToDto(Location location) {
        if(isNull(location)) {
            return null;
        }
        return new LocationDto(
                location.getId(),
                location.getAddress(),
                location.getAddressDetails(),
                location.getName(),
                location.getMaxCapacity()
        );
    }
}
