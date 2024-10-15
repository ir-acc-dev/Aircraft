package com.example.aircraftBackend.service;

import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.repositories.PilotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PilotService {

    private final PilotRepository pilotRepository;

    public Pilot createPilot(Pilot pilot) {
        return pilotRepository.save(pilot);
    }

    public Pilot getPilotById(Long id) {
        return pilotRepository.findById(id).orElse(null);
    }

    public List<Pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    public void deletePilot(Long id) {
        pilotRepository.deleteById(id);
    }
}
