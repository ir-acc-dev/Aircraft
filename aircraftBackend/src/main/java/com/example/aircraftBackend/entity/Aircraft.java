package com.example.aircraftBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airframe")
    private String airframe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pilot_id")
    private Pilot pilot;

}
