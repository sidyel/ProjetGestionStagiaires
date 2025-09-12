package com.ex.demomessageabsence.service;

import com.ex.demomessageabsence.entite.Absence;
import com.ex.demomessageabsence.entite.Stagiaire;
import com.ex.demomessageabsence.feign.EncadrantFeignClient;
import com.ex.demomessageabsence.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;

@Service
@Transactional
public class AbsenceService implements AbsenceInterface {

    private final AbsenceRepository repository;

    @Autowired
    private EncadrantFeignClient encadrantFeignClient;

    public AbsenceService(AbsenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Absence createAbsence(Absence absence) {
        absence.setDateDebut(new Date());
        Stagiaire stagiaire = encadrantFeignClient.getStagiaire(absence.getIdstagiaire());
        absence.setStagiaire(stagiaire);
        return repository.save(absence);
    }

    @Override
    public Absence getAbsenceById(Integer id) {
        Absence absence =  repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence non trouvée avec l'ID : " + id));
        Stagiaire stagiaire=encadrantFeignClient.getStagiaire(absence.getIdstagiaire());
        System.out.println(stagiaire.getIdpersonne());
        absence.setStagiaire(stagiaire);
        return absence;
    }

    @Override
    public List<Absence> getAllAbsences() {
        return repository.findAll();
    }

    @Override
    public List<Absence> getAbsencesByStagiaire(Integer idStagiaire) {
        return repository.findByIdstagiaire(idStagiaire);
    }

    @Override
    public Absence updateAbsence(Integer id, Absence updatedAbsence) {
        Absence existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence non trouvée"));
        existing.setDateDebut(updatedAbsence.getDateDebut());
        existing.setDateFin(updatedAbsence.getDateFin());
        existing.setMotif(updatedAbsence.getMotif());
        existing.setJustification(updatedAbsence.getJustification());
        return repository.save(existing);
    }

    @Override
    public void deleteAbsence(Integer id) {
        Absence absence = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absence non trouvée"));
        repository.delete(absence);
    }
}
