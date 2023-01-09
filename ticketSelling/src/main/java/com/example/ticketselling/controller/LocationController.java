package com.example.ticketselling.controller;

import com.example.ticketselling.dto.LocationDto;
import com.example.ticketselling.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> retrieveLocations() {
        return ResponseEntity.ok().body(locationService.retrieveLocations());
    }

    @PostMapping("/add")
    public ResponseEntity<LocationDto> addLocation(@Valid @RequestBody LocationDto locationDto) {
        System.out.println(locationDto);
        return ResponseEntity.ok().body(locationService.saveLocation(locationDto));
    }

    @PutMapping("/edit/{locationId}")
    public ResponseEntity<LocationDto> editLocation(@PathVariable int locationId, @RequestBody LocationDto editedLocationDto) {
        return ResponseEntity.ok().body(locationService.updateLocation(locationId, editedLocationDto));
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<String> deleteLocation(@PathVariable int locationId) {
        locationService.deleteLocationById(locationId);
        return ResponseEntity.ok().body("Location successfully deleted!");
    }
}
