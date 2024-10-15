package com.example.aircraftBackend.service;

import com.example.aircraftBackend.entity.Aircraft;
import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.repositories.AircraftRepository;
import com.example.aircraftBackend.repositories.PilotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AircraftService {

    private AircraftRepository aircraftRepository;
    private PilotRepository pilotRepository;

    public Aircraft createAircraft(Aircraft aircraft) {

        Pilot pilot = pilotRepository.findById(aircraft.getPilot().getId())
                .orElseThrow(() -> new NoSuchElementException("Pilot not found"));

        aircraft.setPilot(pilot);
        return aircraftRepository.save(aircraft);
    }

    public Aircraft getAircraftById(Long id) {
        return aircraftRepository.findById(id).orElse(null);
    }

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }
}
