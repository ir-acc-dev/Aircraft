package com.example.aircraftBackend.controller;

import com.example.aircraftBackend.entity.Pilot;
import com.example.aircraftBackend.service.PilotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PilotControllerTest {

    @InjectMocks
    private PilotController pilotController;

    @Mock
    private PilotService pilotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPilot_shouldReturnCreatedPilot() {
        Pilot pilot = new Pilot(); // Create a sample Pilot
        pilot.setId(1L); // Assuming you have an ID field
        when(pilotService.createPilot(any(Pilot.class))).thenReturn(pilot);

        ResponseEntity<Pilot> response = pilotController.createPilot(pilot);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pilot, response.getBody());
        verify(pilotService, times(1)).createPilot(any(Pilot.class));
    }

    @Test
    void getPilotById_shouldReturnPilot() {
        Pilot pilot = new Pilot();
        pilot.setId(1L);
        when(pilotService.getPilotById(1L)).thenReturn(pilot);

        ResponseEntity<Pilot> response = pilotController.getPilotById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pilot, response.getBody());
        verify(pilotService, times(1)).getPilotById(1L);
    }

    @Test
    void getAllPilots_shouldReturnListOfPilots() {
        Pilot pilot1 = new Pilot();
        Pilot pilot2 = new Pilot();
        List<Pilot> pilotList = Arrays.asList(pilot1, pilot2);
        when(pilotService.getAllPilots()).thenReturn(pilotList);

        ResponseEntity<List<Pilot>> response = pilotController.getAllPilots();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pilotList, response.getBody());
        verify(pilotService, times(1)).getAllPilots();
    }

    @Test
    void deletePilot_shouldReturnSuccessMessage() {
        doNothing().when(pilotService).deletePilot(1L);

        ResponseEntity<String> response = pilotController.deletePilot(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pilot deleted", response.getBody());
        verify(pilotService, times(1)).deletePilot(1L);
    }
}
