package com.auth.Authenticate.controller;

import com.auth.Authenticate.entity.EventEntity;
import com.auth.Authenticate.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping("/events")
    public List<EventEntity> list() {
        return service.listAll();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventEntity> get(@PathVariable Integer id) {
        try {
            EventEntity event = service.get(id);
            return new ResponseEntity<EventEntity>(event, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<EventEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/events/create")
    public ResponseEntity<EventEntity> create(@RequestBody final EventEntity newEvent) {
        EventEntity event = service.save(newEvent);
        return new ResponseEntity<EventEntity>(event, HttpStatus.OK);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<EventEntity> update(@RequestBody EventEntity event, @PathVariable Integer id) {
        try {
            EventEntity existEvent = service.get(id);
            service.save(event);
            return new ResponseEntity<EventEntity>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/events/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
