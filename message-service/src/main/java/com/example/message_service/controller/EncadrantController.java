package com.example.message_service.controller;

// Contrôleur EncadrantController.java


import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.service.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/encadrants")
public class EncadrantController {

    @Autowired
    private EncadrantService encadrantService;

    @PostMapping
    public ResponseEntity<Encadrant> createEncadrant(@RequestBody Encadrant encadrant) {
        Encadrant savedEncadrant = encadrantService.saveEncadrant(encadrant);
        return new ResponseEntity<>(savedEncadrant, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<Encadrant> chercherEncadrant(@RequestParam String motCle) {
        return encadrantService.rechercherEncadrant(motCle);
    }

    @GetMapping
    public ResponseEntity<List<Encadrant>> getAllEncadrants() {
        List<Encadrant> encadrants = encadrantService.getAllEncadrants();
        return new ResponseEntity<>(encadrants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encadrant> getEncadrantById(@PathVariable Long id) {
        return encadrantService.getEncadrantById(id)
                .map(encadrant -> new ResponseEntity<>(encadrant, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encadrant> updateEncadrant(@PathVariable Long id, @RequestBody Encadrant encadrant) {
        return encadrantService.getEncadrantById(id)
                .map(existingEncadrant -> {
                    encadrant.setIdpersonne(id);
                    Encadrant updatedEncadrant = encadrantService.saveEncadrant(encadrant);
                    return new ResponseEntity<>(updatedEncadrant, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncadrant(@PathVariable Long id) {
        if (encadrantService.getEncadrantById(id).isPresent()) {
            encadrantService.deleteEncadrant(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

