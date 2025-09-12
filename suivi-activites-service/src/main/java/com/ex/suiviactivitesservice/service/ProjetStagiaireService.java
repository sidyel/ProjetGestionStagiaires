package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.entite.ProjetStagiaire;
import com.ex.suiviactivitesservice.repository.ProjetStagiairerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetStagiaireService {
    @Autowired
    private ProjetStagiairerepo repo;

    /** Assigner un stagiaire à un projet */
    public ProjetStagiaire assigner(Integer idprojet, Integer idstagiaire) {
        ProjetStagiaire ps = new ProjetStagiaire();
        ps.setIdprojet(idprojet);
        ps.setIdstagiaire(idstagiaire);
        return repo.save(ps);
    }

    /** Retirer un stagiaire d’un projet */
    public void desassigner(Long id) {
        repo.deleteById(id);
    }

    /** Lister tous les stagiaires affectés à un projet */
    public List<ProjetStagiaire> getAffectationsParProjet(Integer idprojet) {
        return repo.findByIdprojet(idprojet);
    }
}
