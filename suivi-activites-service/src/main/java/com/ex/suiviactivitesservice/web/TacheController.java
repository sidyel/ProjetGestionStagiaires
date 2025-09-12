package com.ex.suiviactivitesservice.web;

import com.ex.suiviactivitesservice.entite.Tache;
import com.ex.suiviactivitesservice.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taches")
@CrossOrigin("*")
public class TacheController {

    @Autowired
    private TacheService tacheService;

    // Créer une tâche
    @PostMapping
    public Tache save(@RequestBody Tache tache) {
        return tacheService.save(tache);
    }

    // Lister toutes les tâches
    @GetMapping
    public List<Tache> findAll() {
        return tacheService.findAll();
    }

    // Récupérer une tâche par ID (avec données enrichies)
    @GetMapping("/{id}")
    public Tache findById(@PathVariable Integer id) {
        return tacheService.findById(id);
    }

    // Supprimer une tâche
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        tacheService.deleteById(id);
    }

    // Mettre à jour une tâche
    @PutMapping("/{id}")
    public Tache update(@PathVariable Integer id, @RequestBody Tache tache) {
        return tacheService.updateTache(id, tache);
    }

    // Rechercher par ID d'encadrant
    @GetMapping("/encadrant/{idencadrant}")
    public List<Tache> findByEncadrant(@PathVariable Integer idencadrant) {
        return tacheService.findByidencadrant(idencadrant);
    }

    // Rechercher par ID de stagiaire
    @GetMapping("/stagiaire/{idstagiaire}")
    public List<Tache> findByStagiaire(@PathVariable Integer idstagiaire) {
        return tacheService.findByidstagiaire(idstagiaire);
    }

    // Rechercher par ID de projet
    @GetMapping("/projet/{idprojet}")
    public List<Tache> findByProjet(@PathVariable Integer idprojet) {
        return tacheService.findByidprojet(idprojet);
    }
}

