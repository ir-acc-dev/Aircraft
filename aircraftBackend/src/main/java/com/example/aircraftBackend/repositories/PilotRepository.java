package com.example.aircraftBackend.repositories;

import com.example.aircraftBackend.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
