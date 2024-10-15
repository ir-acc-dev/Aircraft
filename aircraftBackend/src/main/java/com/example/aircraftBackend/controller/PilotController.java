package com.example.aircraftBackend.controller;

import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.repositories.PilotRepository;
import com.example.aircraftBackend.service.PilotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;
    private final PilotRepository pilotRepository;

    @PostMapping
    public ResponseEntity<Pilot> createPilot(@RequestBody Pilot pilot) {
        Pilot newPilot = pilotService.createPilot(pilot);
        return new ResponseEntity<>(newPilot, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pilot> getPilotById(@PathVariable Long id) {
        Pilot pilot = pilotService.getPilotById(id);
        return ResponseEntity.ok(pilot);
    }

    @GetMapping
    public ResponseEntity<List<Pilot>> getAllPilots() {
        List<Pilot> pilots = pilotService.getAllPilots();
        return ResponseEntity.ok(pilots);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePilot(@PathVariable Long id) {
        pilotService.deletePilot(id);
        return ResponseEntity.ok("Pilot deleted");
    }

}
