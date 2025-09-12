package com.ex.suiviactivitesservice.web;

import com.ex.suiviactivitesservice.entite.ProjetStagiaire;
import com.ex.suiviactivitesservice.service.ProjetStagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets/{idprojet}/stagiaires")
public class ProjetStagiaireController {

    @Autowired
    private ProjetStagiaireService service;

    // assigne un stagiaire
    @PostMapping("/{idstagiaire}")
    public ResponseEntity<ProjetStagiaire> assigner(
            @PathVariable Integer idprojet,
            @PathVariable Integer idstagiaire) {
        ProjetStagiaire ps = service.assigner(idprojet, idstagiaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(ps);
    }

    // liste des affectations pour un projet
    @GetMapping
    public ResponseEntity<List<ProjetStagiaire>> lister(
            @PathVariable Integer idprojet) {
        return ResponseEntity.ok(service.getAffectationsParProjet(idprojet));
    }

    // désaffecte (supprime)
    @DeleteMapping("/{idps}")
    public ResponseEntity<Void> desassigner(@PathVariable Long idps) {
        service.desassigner(idps);
        return ResponseEntity.noContent().build();
    }
}

