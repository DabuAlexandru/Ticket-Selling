package com.example.ticketselling.controller;

import com.example.ticketselling.model.Location;
import com.example.ticketselling.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> retrieveLocations() {
        return ResponseEntity.ok().body(locationService.retrieveLocations());
    }

    @PostMapping("/new")
    public ResponseEntity<?> addLocation(@RequestBody Location location) {
        return ResponseEntity.ok().body(locationService.saveLocation(location));
    }

    @PutMapping("/edit/{locationId}")
    public ResponseEntity<Location> editLocation(@PathVariable int locationId, @RequestBody Location editedLocation) {
        return ResponseEntity.ok().body(locationService.updateLocation(locationId, editedLocation));
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<String> addLocation(@PathVariable int locationId) {
        locationService.deleteLocationById(locationId);
        return ResponseEntity.ok().body("Location successfully deleted!");
    }
}
