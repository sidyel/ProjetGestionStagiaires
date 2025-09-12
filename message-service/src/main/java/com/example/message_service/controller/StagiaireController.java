package com.example.message_service.controller;

// Contrôleur StagiaireController.java


import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.repository.EncadrantRepository;
import com.example.message_service.service.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stagiaires")
public class StagiaireController {

    @Autowired
    private StagiaireService stagiaireService;

    @Autowired
    private EncadrantRepository encadrantRepository;

    @PostMapping
    public Stagiaire ajouter(@RequestBody Stagiaire stagiaire) {
        Long encadrantId = stagiaire.getEncadrant() != null ? stagiaire.getEncadrant().getIdpersonne() : null;

        if (encadrantId != null) {
            Encadrant encadrantComplet = encadrantRepository.findById(encadrantId)
                    .orElseThrow(() -> new RuntimeException("Encadrant non trouvé avec id " + encadrantId));
            stagiaire.setEncadrant(encadrantComplet);
        }

        return stagiaireService.saveStagiaire(stagiaire);
    }

    @GetMapping("/search")
    public List<Stagiaire> chercherStagiaires(@RequestParam String motCle) {
        return stagiaireService.rechercherStagiaires(motCle);
    }


    @GetMapping
    public ResponseEntity<List<Stagiaire>> getAllStagiaires() {
        List<Stagiaire> stagiaires = stagiaireService.getAllStagiaires();
        return new ResponseEntity<>(stagiaires, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stagiaire> getStagiaireById(@PathVariable Long id) {
        return stagiaireService.getStagiaireById(id)
                .map(stagiaire -> new ResponseEntity<>(stagiaire, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stagiaire> updateStagiaire(@PathVariable Long id, @RequestBody Stagiaire stagiaire) {
        return stagiaireService.getStagiaireById(id)
                .map(existingStagiaire -> {
                    stagiaire.setIdpersonne(id);
                    Stagiaire updatedStagiaire = stagiaireService.saveStagiaire(stagiaire);
                    return new ResponseEntity<>(updatedStagiaire, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStagiaire(@PathVariable Long id) {
        if (stagiaireService.getStagiaireById(id).isPresent()) {
            stagiaireService.deleteStagiaire(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/encadrant/{id}")
    public List<Stagiaire> getMesStagiaire(@PathVariable Long id) {
        return stagiaireService.getStagiairesByEncadrant(id);
    }

    @GetMapping("/{id}/encadrant")
    public ResponseEntity<Encadrant> getEncadrantOfStagiaire(@PathVariable Long id) {
        return (ResponseEntity<Encadrant>) stagiaireService.getStagiaireById(id)
                .map(stag -> {
                    Encadrant enc = stag.getEncadrant();
                    return enc != null
                            ? ResponseEntity.ok(enc)
                            : ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

