package com.example.message_service.controller;

// Contrôleur ChefRhController.java


import com.example.message_service.entity.ChefRh;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.service.ChefRhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chefrhs")
public class ChefRhController {

    @Autowired
    private ChefRhService chefRhService;

    @PostMapping
    public ResponseEntity<ChefRh> createChefRh(@RequestBody ChefRh chefRh) {
        ChefRh savedChefRh = chefRhService.saveChefRh(chefRh);
        return new ResponseEntity<>(savedChefRh, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public List<ChefRh> chercherChefrh(@RequestParam String motCle) {
        return chefRhService.rechercherChehrh(motCle);
    }

    @GetMapping
    public ResponseEntity<List<ChefRh>> getAllChefRhs() {
        List<ChefRh> chefRhs = chefRhService.getAllChefRhs();
        return new ResponseEntity<>(chefRhs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChefRh> getChefRhById(@PathVariable Long id) {
        return chefRhService.getChefRhById(id)
                .map(chefRh -> new ResponseEntity<>(chefRh, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChefRh> updateChefRh(@PathVariable Long id, @RequestBody ChefRh chefRh) {
        return chefRhService.getChefRhById(id)
                .map(existingChefRh -> {
                    chefRh.setIdpersonne(id);
                    ChefRh updatedChefRh = chefRhService.saveChefRh(chefRh);
                    return new ResponseEntity<>(updatedChefRh, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChefRh(@PathVariable Long id) {
        if (chefRhService.getChefRhById(id).isPresent()) {
            chefRhService.deleteChefRh(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
