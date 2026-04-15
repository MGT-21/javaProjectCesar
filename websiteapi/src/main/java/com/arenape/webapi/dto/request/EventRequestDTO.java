package com.arenape.webapi.dto.request;

public record EventRequestDTO(
    String name,
    String location,
    String description,
    String imageUrl,
    Double price,
    Integer availableTickets
) {}