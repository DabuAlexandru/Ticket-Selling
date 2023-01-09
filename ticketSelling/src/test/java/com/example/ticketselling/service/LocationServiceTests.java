package com.example.ticketselling.service;

import com.example.ticketselling.constants.LocationConstants;
import com.example.ticketselling.dto.LocationDto;
import com.example.ticketselling.mapper.LocationMapper;
import com.example.ticketselling.model.BoughtTicket;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.repository.LocationRepository;
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
public class LocationServiceTests {
    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private LocationMapper locationMapper;

    @Test
    @DisplayName("Retrieve all locations OK")
    void getAllLocations() {
        // arrange
        List<Location> locationList = List.of(Seeder.location);
        List<LocationDto> locationDtoList = List.of(Seeder.locationDto);

        // act
        when(locationRepository.findAll()).thenReturn(locationList);

        when(locationMapper.convertToDto(Seeder.location)).thenReturn(Seeder.locationDto);

        // assert
        List<LocationDto> result = locationService.retrieveLocations();
        assertEquals(result, locationDtoList);
    }

    @Test
    @DisplayName("update location OK")
    void updateLocation() {
        // arrange
        int locationId = 1;
        Location location = Seeder.location;
        LocationDto editedLocationDto = new LocationDto(1, location.getAddress(), location.getAddressDetails(), "Some other name", location.getMaxCapacity());

        // act
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));
        when(locationRepository.save(location)).thenReturn(location);
        when(locationMapper.convertToDto(location)).thenReturn(editedLocationDto);

        // assert
        LocationDto result = locationService.updateLocation(locationId, editedLocationDto);
        assertEquals(result, editedLocationDto);
    }

    @Test
    @DisplayName("update location FAIL")
    void updateLocationFail() {
        // arrange
        int locationId = 1;
        Location location = null;
        LocationDto editedLocationDto = Seeder.locationDto;

        // act
        when(locationRepository.findById(locationId)).thenReturn(Optional.ofNullable(location));

        // assert
        RuntimeException locationNotFound = assertThrows(RuntimeException.class, () -> locationService.updateLocation(locationId, editedLocationDto));
        assertEquals(locationNotFound.getMessage(), LocationConstants.LOCATION_NOT_FOUND_MESSAGE);
    }

    @Test
    @DisplayName("save location OK")
    void saveLocation()
    {
        // arrange
        Location location = Seeder.location;
        LocationDto locationDto = Seeder.locationDto;

        // act
        when(locationMapper.convertFromDto(locationDto)).thenReturn(location);
        when(locationRepository.save(location)).thenReturn(location);
        when(locationMapper.convertToDto(location)).thenReturn(locationDto);

        // assert
        LocationDto result = locationService.saveLocation(locationDto);
        assertEquals(result, locationDto);
    }

    @Test
    @DisplayName("delete location OK")
    void deleteLocationById() {
        // arrange
        int locationId = 1;
        Location location = Seeder.location;

        // act
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        // assert
        String result = locationService.deleteLocationById(locationId);
        assertEquals(result, LocationConstants.DELETE_OK_MESSAGE);
    }

    @Test
    @DisplayName("delete location FAIL")
    void deleteLocationFail() {
        // arrange
        int locationId = 1;
        Location location = null;

        // act
        when(locationRepository.findById(locationId)).thenReturn(Optional.ofNullable(location));

        // assert
        RuntimeException locationNotFound = assertThrows(RuntimeException.class, () -> locationService.deleteLocationById(locationId));
        assertEquals(locationNotFound.getMessage(), LocationConstants.LOCATION_NOT_FOUND_MESSAGE);
    }
}
