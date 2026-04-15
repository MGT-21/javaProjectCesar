package com.arenape.webapi.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.arenape.webapi.entity.enums.EventStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

public record EventRequestDTO(
    String name,
    String location,
    String description,
    String imageUrl,
    BigDecimal price,
    Integer availableTickets,
    EventStatus status,
    
    @Schema(example = "2026-04-15T19:43")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime eventDate
) {}