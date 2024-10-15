package com.example.aircraftBackend.repositories;

import com.example.aircraftBackend.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
