package com.example.ticketselling.service;

import com.example.ticketselling.dto.LocationDto;
import com.example.ticketselling.mapper.LocationMapper;
import com.example.ticketselling.model.Location;
import com.example.ticketselling.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public List<LocationDto> retrieveLocations() {
        return locationRepository.findAll()
                .stream().map(locationMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public LocationDto findLocationById(int locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found!"));
        return locationMapper.convertToDto(location);
    }

    public LocationDto updateLocation(int locationId, LocationDto updatedLocation) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found!"));
        if (!isNull(updatedLocation.getAddress())) {
            location.setAddress(updatedLocation.getAddress());
        }
        if (!isNull(updatedLocation.getAddressDetails())) {
            location.setAddressDetails(updatedLocation.getAddressDetails());
        }
        if (!isNull(updatedLocation.getName())) {
            location.setName(updatedLocation.getName());
        }
        if (updatedLocation.getMaxCapacity() != 0) {
            location.setMaxCapacity(updatedLocation.getMaxCapacity());
        }
        return locationMapper.convertToDto(locationRepository.save(location));
    }

    public LocationDto saveLocation(LocationDto location) {
        return locationMapper.convertToDto(locationRepository.save(locationMapper.convertFromDto(location)));
    }

    public void deleteLocationById(Integer locationId) {
        locationRepository.deleteById(locationId);
    }
}
