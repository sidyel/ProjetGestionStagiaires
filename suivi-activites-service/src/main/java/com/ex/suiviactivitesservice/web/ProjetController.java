package com.ex.suiviactivitesservice.web;

import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import com.ex.suiviactivitesservice.service.PojetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projets")
@CrossOrigin("*")
public class ProjetController {

    @Autowired
    private PojetService pojetService;

    // Ajouter un projet
    @PostMapping
    public Projet save(@RequestBody Projet projet) {
        return pojetService.save(projet);
    }

    // Lister tous les projets
    @GetMapping
    public List<Projet> findAll() {
        return pojetService.findAll();
    }

    // Récupérer un projet par ID
    @GetMapping("/{id}")
    public Projet findById(@PathVariable Integer id) {
        return pojetService.findById(id);
    }

    // Supprimer un projet
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        pojetService.deleteById(id);
    }

    // Mettre à jour un projet
    @PutMapping("/{id}")
    public Projet update(@PathVariable Integer id, @RequestBody Projet projet) {
        return pojetService.updateTache(id, projet);
    }

    // Lister les projets par encadrant
    @GetMapping("/encadrant/{idencadrant}")
    public List<Projet> findByEncadrant(@PathVariable Integer idencadrant) {
        return pojetService.findByidencadrant(idencadrant);
    }

    // Lister les projets d’un stagiaire
    @GetMapping("/stagiaire/{idstagiaire}")
    public List<Projet> findByStagiaire(@PathVariable Integer idstagiaire) {
        return pojetService.findByidstagiaire(idstagiaire);
    }

    // Lister les stagiaires d’un projet
    @GetMapping("/stagiaires/{idprojet}")
    public List<Stagiaire> findStagiairesByProjet(@PathVariable Integer idprojet) {
        return pojetService.findByidprojet(idprojet);
    }
}

