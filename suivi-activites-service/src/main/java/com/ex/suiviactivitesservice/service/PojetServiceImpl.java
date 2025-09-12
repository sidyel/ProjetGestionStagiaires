package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.entite.ProjetStagiaire;
import com.ex.suiviactivitesservice.feignClient.EncadrantFeignClient;
import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import com.ex.suiviactivitesservice.repository.ProjetRepository;
import com.ex.suiviactivitesservice.repository.ProjetStagiairerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PojetServiceImpl implements PojetService {

    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ProjetStagiairerepo projetStagiairerepo;
    @Autowired
    EncadrantFeignClient encadrantFeignClient;


    @Override
    public Projet save(Projet projet) {
        return projetRepository.save(projet);
    }

    @Override
    public List<Projet> findAll() {
        return projetRepository.findAll();
    }

    @Override
    public Projet findById(Integer id) {
        Projet projet = projetRepository.findById(id).get();
        Encadrant encadrant=encadrantFeignClient.getEncadrant(projet.getIdencadrant());
        projet.setEncadrant(encadrant);
        return projet;
    }

    @Override
    public void deleteById(Integer id) {
        projetRepository.deleteById(id);

    }

    @Override
    public List<Projet> findByidencadrant(Integer idencadrant) {
        return projetRepository.findByidencadrant(idencadrant);
    }

    @Override
    public Projet updateTache(Integer id, Projet tache) {
        Projet projet1 = projetRepository.findById(id).get();
        projet1.setTitre(tache.getTitre());
        projet1.setDescription(tache.getDescription());
        projet1.setDateFin(tache.getDateFin());
        projet1.setDateDebut(tache.getDateDebut());

        return projetRepository.save(projet1);
    }

    @Override
    public List<Projet> findByidstagiaire(Integer idstagiaire) {
        List<ProjetStagiaire> relations = projetStagiairerepo.findByIdstagiaire(idstagiaire);
        return relations.stream()
                .map(rel -> projetRepository.findById(rel.getIdprojet()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }


    @Override
    public List<Stagiaire> findByidprojet(Integer idprojet) {
        List<ProjetStagiaire> relations = projetStagiairerepo.findByIdprojet(idprojet);
        return relations.stream()
                .map(rel -> encadrantFeignClient.getStagiaire(rel.getIdstagiaire()))
                .toList();
    }

}
