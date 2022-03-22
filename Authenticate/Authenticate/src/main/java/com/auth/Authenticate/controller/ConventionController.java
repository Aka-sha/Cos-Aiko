package com.auth.Authenticate.controller;

/**
 * This is a controller class with endpoints for the CONVENTIONS (cons) table in our MySQL database.
 * Any CRUD operation performed to this table by the CosAiko application will be routed
 * via this ConventionController class. These conventions can be added to the users calendar
 */

import com.auth.Authenticate.entity.ConventionEntity;
import com.auth.Authenticate.service.ConventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping
public class ConventionController {
    @Autowired
    private ConventionService service;

    /**
     * This function returns a list of all current conventions
     *
     * @return list of all conventions
     */
    @GetMapping("/cons")
    public List<ConventionEntity> list() {
        return service.listAll();
    }

    /**
     * This function selects a convention from the DB by its unique id
     *
     * @param id the convention's unique id
     * @return convention and httpstatus (OK = 200 and NOT_FOUND = 404)
     */
    @GetMapping("/cons/{id}")
    public ResponseEntity<ConventionEntity> get(@PathVariable Integer id) {
        try {
            ConventionEntity con = service.get(id);
            return new ResponseEntity<ConventionEntity>(con, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ConventionEntity>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This function creates a new convention entity
     *
     * @param newCon convention to be added to DB table cons
     * @return convention and httpstatus
     */
    @PostMapping("/cons/create")
    public ResponseEntity<ConventionEntity> create(@RequestBody final ConventionEntity newCon) {
        ConventionEntity con = service.save(newCon);
        return new ResponseEntity<ConventionEntity>(con, HttpStatus.OK);
    }

    /**
     * This function allows for updates to a convention entity
     *
     * @param con convention being updated
     * @param id id of convention being updated
     * @return updated convention information and http status code (OK = 200, NOT_FOUND = 404)
     */
    @PutMapping("/cons/{id}")
    public ResponseEntity<?> update(@RequestBody ConventionEntity con, @PathVariable Integer id) {
        try {
            ConventionEntity existCon = service.get(id);
            service.save(con);
            return new ResponseEntity<ConventionEntity>(con, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ConventionEntity>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * This convention deletes a convention by its unique id
     *
     * @param id the convention entity's unique id
     */
    @DeleteMapping("/cons/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
