package com.example.message_service.controller;

// Contrôleur PersonneController.java


import com.example.message_service.entity.Personne;
import com.example.message_service.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    // Créer une nouvelle personne
    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        Personne savedPersonne = personneService.savePersonne(personne);
        return new ResponseEntity<>(savedPersonne, HttpStatus.CREATED);
    }

    // Récupérer toutes les personnes
    @GetMapping
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        List<Personne> personnes = personneService.getAllPersonnes();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    // Récupérer une personne par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        return personneService.getPersonneById(id)
                .map(personne -> new ResponseEntity<>(personne, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour une personne existante
    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personne) {
        return personneService.getPersonneById(id)
                .map(existingPersonne -> {
                    personne.setIdpersonne(id); // Assurez-vous que l'ID est correct
                    Personne updatedPersonne = personneService.savePersonne(personne);
                    return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Supprimer une personne par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        if (personneService.getPersonneById(id).isPresent()) {
            personneService.deletePersonne(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

