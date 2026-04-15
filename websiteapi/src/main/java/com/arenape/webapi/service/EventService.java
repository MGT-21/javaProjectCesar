package com.arenape.webapi.service;

import com.arenape.webapi.dto.request.EventRequestDTO;
import com.arenape.webapi.dto.response.EventResponseDTO;
import com.arenape.webapi.entity.Event;
import com.arenape.webapi.entity.enums.EventStatus;
import com.arenape.webapi.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public EventResponseDTO create(EventRequestDTO request) {

        Event event = new Event();

        event.setName(request.name());
        event.setLocation(request.location());
        event.setDescription(request.description());
        event.setImageUrl(request.imageUrl());
        event.setPrice(request.price());
        event.setAvailableTickets(request.availableTickets());
        event.setEventDate(request.eventDate());

        event.setStatus(EventStatus.PENDING);

        Event saved = repository.save(event);

        return new EventResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getLocation(),
                saved.getDescription(),
                saved.getImageUrl(),
                saved.getPrice(),
                saved.getAvailableTickets(),
                saved.getStatus().name(),
                saved.getEventDate());
    }

    public List<EventResponseDTO> findAll() {

        List<Event> events = repository.findAll();

        return events.stream()
                .map(event -> new EventResponseDTO(
                        event.getId(),
                        event.getName(),
                        event.getLocation(),
                        event.getDescription(),
                        event.getImageUrl(),
                        event.getPrice(),
                        event.getAvailableTickets(),
                        event.getStatus().name(),
                        event.getEventDate()))
                .toList();
    }

    public EventResponseDTO findById(Long id) {

        Event event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return new EventResponseDTO(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getDescription(),
                event.getImageUrl(),
                event.getPrice(),
                event.getAvailableTickets(),
                event.getStatus().name(),
                event.getEventDate());
    }

    public EventResponseDTO update(Long id, EventRequestDTO request) {

        Event event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setName(request.name());
        event.setLocation(request.location());
        event.setDescription(request.description());
        event.setImageUrl(request.imageUrl());
        event.setPrice(request.price());
        event.setAvailableTickets(request.availableTickets());
        event.setEventDate(request.eventDate());

        // NÃO mexe no id
        // NÃO mexe no status (se quiser manter regra do backend)

        Event updated = repository.save(event);

        return new EventResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getLocation(),
                updated.getDescription(),
                updated.getImageUrl(),
                updated.getPrice(),
                updated.getAvailableTickets(),
                updated.getStatus().name(),
                updated.getEventDate());
    }

    public void delete(Long id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        repository.delete(event);
    }
}