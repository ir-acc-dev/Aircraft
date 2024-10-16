package com.example.aircraftBackend.service;

import com.example.aircraftBackend.entity.Aircraft;
import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.repositories.AircraftRepository;
import com.example.aircraftBackend.repositories.PilotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AircraftServiceTest {

    @InjectMocks
    private AircraftService aircraftService;

    @Mock
    private AircraftRepository aircraftRepository;

    @Mock
    private PilotRepository pilotRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAircraft_shouldReturnCreatedAircraft() {
        Pilot pilot = new Pilot();
        pilot.setId(1L);

        Aircraft aircraft = new Aircraft();
        aircraft.setPilot(pilot);

        when(pilotRepository.findById(1L)).thenReturn(Optional.of(pilot));
        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(aircraft);

        Aircraft createdAircraft = aircraftService.createAircraft(aircraft);

        assertNotNull(createdAircraft);
        assertEquals(pilot, createdAircraft.getPilot());
        verify(pilotRepository, times(1)).findById(1L);
        verify(aircraftRepository, times(1)).save(any(Aircraft.class));
    }

    @Test
    void createAircraft_shouldThrowException_WhenPilotNotFound() {
        Aircraft aircraft = new Aircraft();
        aircraft.setPilot(new Pilot());
        aircraft.getPilot().setId(1L);

        when(pilotRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            aircraftService.createAircraft(aircraft);
        });

        assertEquals("Pilot not found", exception.getMessage());
        verify(pilotRepository, times(1)).findById(1L);
        verify(aircraftRepository, never()).save(any(Aircraft.class));
    }

    @Test
    void getAircraftById_shouldReturnAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));

        Aircraft foundAircraft = aircraftService.getAircraftById(1L);

        assertNotNull(foundAircraft);
        assertEquals(aircraft.getId(), foundAircraft.getId());
        verify(aircraftRepository, times(1)).findById(1L);
    }

    @Test
    void getAircraftById_shouldReturnNull_WhenAircraftNotFound() {
        when(aircraftRepository.findById(1L)).thenReturn(Optional.empty());

        Aircraft foundAircraft = aircraftService.getAircraftById(1L);

        assertNull(foundAircraft);
        verify(aircraftRepository, times(1)).findById(1L);
    }

    @Test
    void getAllAircrafts_shouldReturnListOfAircrafts() {
        Aircraft aircraft1 = new Aircraft();
        Aircraft aircraft2 = new Aircraft();
        List<Aircraft> aircraftList = Arrays.asList(aircraft1, aircraft2);

        when(aircraftRepository.findAll()).thenReturn(aircraftList);

        List<Aircraft> result = aircraftService.getAllAircrafts();

        assertEquals(2, result.size());
        assertEquals(aircraftList, result);
        verify(aircraftRepository, times(1)).findAll();
    }

    @Test
    void deleteAircraft_shouldDeleteAircraft() {
        doNothing().when(aircraftRepository).deleteById(1L);

        aircraftService.deleteAircraft(1L);

        verify(aircraftRepository, times(1)).deleteById(1L);
    }
}
