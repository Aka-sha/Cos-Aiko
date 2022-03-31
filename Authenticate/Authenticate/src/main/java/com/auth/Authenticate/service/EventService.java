package com.auth.Authenticate.service;

/**
 * This class serves as a bridge between the EventController and the EventRepository
 */

import com.auth.Authenticate.dao.EventRepository;
import com.auth.Authenticate.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("eventService")
public class EventService {
    @Autowired
    private EventRepository repo;

    /**
     * Create a new event
     *
     * @param event event to save to database
     * @return the newly created event
     */
    public EventEntity save(EventEntity event) {
        repo.save(event);
        return event;
    }

    /**
     * Select all events from events table in DB
     *
     * @return a list of all currently listed event in database
     */
    public List<EventEntity> listAll() {
        return repo.findAll();
    }

    /**
     * Select an event by its unique id
     *
     * @param eid the events unique id
     * @return the event
     */
    public EventEntity get(Integer eid) {
        return repo.findById(eid).get();
    }

    /**
     * Delete the event by its unique id
     *
     * @param eid the events unique id
     */
    public void delete(Integer eid) {
        repo.deleteById(eid);
    }
}
