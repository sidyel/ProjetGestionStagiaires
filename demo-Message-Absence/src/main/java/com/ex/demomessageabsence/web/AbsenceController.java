package com.ex.demomessageabsence.web;

import com.ex.demomessageabsence.entite.Absence;
import com.ex.demomessageabsence.service.AbsenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/absences")
public class AbsenceController {

    private final AbsenceService service;

    public AbsenceController(AbsenceService service) {
        this.service = service;
    }

    // ✅ Créer une absence
    @PostMapping
    public Absence createAbsence(@RequestBody Absence absence) {
        return service.createAbsence(absence);
    }

    // ✅ Récupérer toutes les absences
    @GetMapping
    public List<Absence> getAllAbsences() {
        return service.getAllAbsences();
    }

    // ✅ Récupérer une absence par son ID
    @GetMapping("/{id}")
    public Absence getAbsenceById(@PathVariable Integer id) {
        return service.getAbsenceById(id);
    }

    // ✅ Récupérer les absences d’un stagiaire donné
    @GetMapping("/stagiaire/{idStagiaire}")
    public List<Absence> getAbsencesByStagiaire(@PathVariable Integer idStagiaire) {
        return service.getAbsencesByStagiaire(idStagiaire);
    }

    // ✅ Modifier une absence existante
    @PutMapping("/{id}")
    public Absence updateAbsence(@PathVariable Integer id, @RequestBody Absence updatedAbsence) {
        return service.updateAbsence(id, updatedAbsence);
    }

    // ✅ Supprimer une absence
    @DeleteMapping("/{id}")
    public void deleteAbsence(@PathVariable Integer id) {
        service.deleteAbsence(id);
    }
}
