package com.example.aircraftBackend.controller;

import com.example.aircraftBackend.entity.Aircraft;
import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.service.AircraftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/aircrafts")
public class AircraftController {

    private AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<Aircraft> createAircraft(@RequestBody Aircraft aircraft) {
        Aircraft newAircraft = aircraftService.createAircraft(aircraft);
        return new ResponseEntity<>(newAircraft, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        return ResponseEntity.ok(aircraft);
    }

    @GetMapping
    public ResponseEntity<List<Aircraft>> getAllAircrafts() {
        List<Aircraft> aircrafts = aircraftService.getAllAircrafts();
        return ResponseEntity.ok(aircrafts);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAircraft(@PathVariable Long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.ok("Aircraft deleted");
    }
}
