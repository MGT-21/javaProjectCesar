package com.arenape.webapi.service;

import com.arenape.webapi.entity.Event;
import com.arenape.webapi.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public Event create(Event event) {
        return repository.save(event);
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