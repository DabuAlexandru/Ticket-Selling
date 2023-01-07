package com.example.ticketselling.service;

import com.example.ticketselling.model.Location;
import com.example.ticketselling.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> retrieveLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(int locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found!"));
    }

    public Location updateLocation(int locationId, Location updatedLocation) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found!"));
        if(!isNull(updatedLocation.getAddress())) {
            location.setAddress(updatedLocation.getAddress());
        }
        if(!isNull(updatedLocation.getAddressDetails())) {
            location.setAddress(updatedLocation.getAddressDetails());
        }
        if(!isNull(updatedLocation.getName())) {
            location.setAddress(updatedLocation.getName());
        }
        if(updatedLocation.getMaxCapacity() != 0) {
            location.setMaxCapacity(updatedLocation.getMaxCapacity());
        }
        return locationRepository.save(location);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocationById(Integer locationId) {
        locationRepository.deleteById(locationId);
    }
}
