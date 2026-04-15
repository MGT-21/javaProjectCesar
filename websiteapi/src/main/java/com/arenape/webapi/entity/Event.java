package com.arenape.webapi.entity;

import com.arenape.webapi.entity.enums.EventStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String name;
    private String location;
    private String description;
    private Double price;
    private Integer availableTickets;

    @Enumerated(EnumType.STRING)
    private EventStatus status;
}