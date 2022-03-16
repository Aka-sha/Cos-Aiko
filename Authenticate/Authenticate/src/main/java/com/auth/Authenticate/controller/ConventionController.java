package com.auth.Authenticate.controller;

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

    @GetMapping("/cons")
    public List<ConventionEntity> list() {
        return service.listAll();
    }

    @GetMapping("/cons/{id}")
    public ResponseEntity<ConventionEntity> get(@PathVariable Integer id) {
        try {
            ConventionEntity con = service.get(id);
            return new ResponseEntity<ConventionEntity>(con, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ConventionEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cons/create")
    public ResponseEntity<ConventionEntity> create(@RequestBody final ConventionEntity newCon) {
        ConventionEntity con = service.save(newCon);
        return new ResponseEntity<ConventionEntity>(con, HttpStatus.OK);
    }

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

    @DeleteMapping("/cons/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
