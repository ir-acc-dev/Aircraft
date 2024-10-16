package com.example.aircraftBackend.service;

import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.repositories.PilotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PilotServiceTest {

    @InjectMocks
    private PilotService pilotService;

    @Mock
    private PilotRepository pilotRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPilot_shouldReturnCreatedPilot() {
        Pilot pilot = new Pilot(); // Create a sample Pilot
        pilot.setId(1L); // Assuming you have an ID field

        when(pilotRepository.save(any(Pilot.class))).thenReturn(pilot);

        Pilot createdPilot = pilotService.createPilot(pilot);

        assertNotNull(createdPilot);
        assertEquals(pilot.getId(), createdPilot.getId());
        verify(pilotRepository, times(1)).save(any(Pilot.class));
    }

    @Test
    void getPilotById_shouldReturnPilot() {
        Pilot pilot = new Pilot();
        pilot.setId(1L);

        when(pilotRepository.findById(1L)).thenReturn(Optional.of(pilot));

        Pilot foundPilot = pilotService.getPilotById(1L);

        assertNotNull(foundPilot);
        assertEquals(pilot.getId(), foundPilot.getId());
        verify(pilotRepository, times(1)).findById(1L);
    }

    @Test
    void getPilotById_shouldReturnNull_WhenPilotNotFound() {
        when(pilotRepository.findById(1L)).thenReturn(Optional.empty());

        Pilot foundPilot = pilotService.getPilotById(1L);

        assertNull(foundPilot);
        verify(pilotRepository, times(1)).findById(1L);
    }

    @Test
    void getAllPilots_shouldReturnListOfPilots() {
        Pilot pilot1 = new Pilot();
        Pilot pilot2 = new Pilot();
        List<Pilot> pilotList = Arrays.asList(pilot1, pilot2);

        when(pilotRepository.findAll()).thenReturn(pilotList);

        List<Pilot> result = pilotService.getAllPilots();

        assertEquals(2, result.size());
        assertEquals(pilotList, result);
        verify(pilotRepository, times(1)).findAll();
    }

    @Test
    void deletePilot_shouldDeletePilot() {
        doNothing().when(pilotRepository).deleteById(1L);

        pilotService.deletePilot(1L);

        verify(pilotRepository, times(1)).deleteById(1L);
    }
}
