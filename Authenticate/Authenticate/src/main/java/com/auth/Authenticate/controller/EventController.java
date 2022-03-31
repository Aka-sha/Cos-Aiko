package com.auth.Authenticate.controller;

/**
 * This is a controller class with endpoints for the EVENTS table in our MySQL database.
 * Any CRUD operation performed to this table by the CosAiko application will be routed
 * via this EventController class. These events are for a user calendar (calendar events)
 */

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

    /**
     * This function returns a list of all existing events
     *
     * @return list of all events
     */
    @GetMapping("/events")
    public List<EventEntity> list() {
        return service.listAll();
    }

    /**
     * This function looks up an event by id
     *
     * @param id unique id of event
     * @return event and status code (OK = 200 and NOT_FOUND = 404)
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<EventEntity> get(@PathVariable Integer id) {
        try {
            EventEntity event = service.get(id);
            return new ResponseEntity<EventEntity>(event, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<EventEntity>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function creates a new event
     *
     * @param newEvent the event being created
     * @return the event and an httpstatus code (OK = 200)
     */
    @PostMapping("/events/create")
    public ResponseEntity<EventEntity> create(@RequestBody final EventEntity newEvent) {
        EventEntity event = service.save(newEvent);
        return new ResponseEntity<EventEntity>(event, HttpStatus.OK);
    }

    /**
     * This function updates an event
     *
     * @param event the event being updated
     * @param id the id of the event being updated
     * @return the updated event and an httpstatus code (OK = 200 and NOT_FOUND = 404)
     */
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

    /**
     * This function deletes and event by its unique id
     *
     * @param id unique id of event
     */
    @DeleteMapping("/events/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
