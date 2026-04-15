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
        saved.getStatus().name()
    );
}

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Event findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public Event update(Long id, Event event) {
        Event existing = findById(id);

        existing.setName(event.getName());
        existing.setLocation(event.getLocation());
        existing.setPrice(event.getPrice());
        existing.setAvailableTickets(event.getAvailableTickets());

        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}