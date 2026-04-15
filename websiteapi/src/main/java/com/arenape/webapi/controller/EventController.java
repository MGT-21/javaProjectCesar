package com.arenape.webapi.controller;

import com.arenape.webapi.dto.request.EventRequestDTO;
import com.arenape.webapi.dto.response.EventResponseDTO;
import com.arenape.webapi.entity.Event;
import com.arenape.webapi.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public EventResponseDTO create(@RequestBody EventRequestDTO request) {
        return service.create(request);
    }

    @GetMapping
    public List<Event> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        return service.update(id, event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}