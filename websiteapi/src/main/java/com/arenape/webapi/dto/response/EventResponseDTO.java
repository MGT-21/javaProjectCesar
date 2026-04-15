package com.arenape.webapi.dto.response;

public record EventResponseDTO(
    Long id,
    String name,
    String location,
    String description,
    String imageUrl,
    Double price,
    Integer availableTickets,
    String status
) {}