package com.example.aircraftBackend.controller;

import com.example.aircraftBackend.entity.Aircraft;
import com.example.aircraftBackend.service.AircraftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

class AircraftControllerTest {

    @InjectMocks
    private AircraftController aircraftController;

    @Mock
    private AircraftService aircraftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAircraft_shouldReturnCreatedAircraft() {
        Aircraft aircraft = new Aircraft(); // Create a sample Aircraft
        aircraft.setId(1L); // Assuming you have an ID field
        when(aircraftService.createAircraft(any(Aircraft.class))).thenReturn(aircraft);

        ResponseEntity<Aircraft> response = aircraftController.createAircraft(aircraft);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(aircraft, response.getBody());
        verify(aircraftService, times(1)).createAircraft(any(Aircraft.class));
    }

    @Test
    void getAircraftById_shouldReturnAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        when(aircraftService.getAircraftById(1L)).thenReturn(aircraft);

        ResponseEntity<Aircraft> response = aircraftController.getAircraftById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(aircraft, response.getBody());
        verify(aircraftService, times(1)).getAircraftById(1L);
    }

    @Test
    void getAllAircrafts_shouldReturnListOfAircrafts() {
        Aircraft aircraft1 = new Aircraft();
        Aircraft aircraft2 = new Aircraft();
        List<Aircraft> aircraftList = Arrays.asList(aircraft1, aircraft2);
        when(aircraftService.getAllAircrafts()).thenReturn(aircraftList);

        ResponseEntity<List<Aircraft>> response = aircraftController.getAllAircrafts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(aircraftList, response.getBody());
        verify(aircraftService, times(1)).getAllAircrafts();
    }

    @Test
    void deleteAircraft_shouldReturnSuccessMessage() {
        doNothing().when(aircraftService).deleteAircraft(1L);

        ResponseEntity<String> response = aircraftController.deleteAircraft(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Aircraft deleted", response.getBody());
        verify(aircraftService, times(1)).deleteAircraft(1L);
    }
}
