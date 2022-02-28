package com.auth.Authenticate.service;

import com.auth.Authenticate.dao.EventRepository;
import com.auth.Authenticate.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("eventService")
public class EventService {
    @Autowired
    private EventRepository repo;

    public EventEntity save(EventEntity event) {
        repo.save(event);
        return event;
    }

    public List<EventEntity> listAll() {
        return repo.findAll();
    }

    public EventEntity get(Integer eid) {
        return repo.findById(eid).get();
    }

    public void delete(Integer eid) {
        repo.deleteById(eid);
    }
}
