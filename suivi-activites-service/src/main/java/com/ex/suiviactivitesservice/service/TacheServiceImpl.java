package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.entite.Tache;
import com.ex.suiviactivitesservice.feignClient.EncadrantFeignClient;
import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import com.ex.suiviactivitesservice.repository.ProjetRepository;
import com.ex.suiviactivitesservice.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TacheServiceImpl implements TacheService {
    @Autowired
    public TacheRepository  tacheRepository;

    @Autowired
    ProjetRepository projetRepository;


    @Autowired
    EncadrantFeignClient encadrantFeignClient;


    @Override
    public Tache save(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public List<Tache> findAll() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache findById(Integer id) {
        Tache tache = tacheRepository.findById(id).get();
        Stagiaire stagiaire = encadrantFeignClient.getStagiaire(tache.getIdstagiaire());
        Encadrant encadrant=encadrantFeignClient.getEncadrant(tache.getIdencadrant());
        Projet projet=projetRepository.findById(tache.getIdprojet()).get();

        tache.setStagiaire(stagiaire);
        tache.setEncadrant(encadrant);
        tache.setProjet(projet);

        return tache;
    }

    @Override
    public void deleteById(Integer id) {
        tacheRepository.deleteById(id);

    }

    @Override
    public List<Tache> findByidencadrant(Integer idencadrant) {
        return tacheRepository.findByIdencadrant(idencadrant);
    }

    @Override
    public List<Tache> findByidstagiaire(Integer idstagiaire) {
        return tacheRepository.findByIdstagiaire(idstagiaire);
    }



    @Override
    public Tache updateTache(Integer id, Tache tache) {
        Tache tache1 = tacheRepository.findById(id).get();
        tache1.setTitre(tache.getTitre());
        tache1.setDescription(tache.getDescription());
        tache1.setDateDebut(tache.getDateDebut());
        tache1.setDateFin(tache.getDateFin());
        tache1.setStatut(tache.getStatut());
        return tacheRepository.save(tache1);


    }

    @Override
    public List<Tache> findByidprojet(Integer idprojet) {
        return tacheRepository.findByIdprojet(idprojet);
    }


}
