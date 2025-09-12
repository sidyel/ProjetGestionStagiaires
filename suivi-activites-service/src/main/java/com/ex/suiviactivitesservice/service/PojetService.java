package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.entite.Tache;
import com.ex.suiviactivitesservice.objet.Stagiaire;

import java.util.List;

public interface PojetService {

    public Projet save(Projet projet);
    public List<Projet> findAll();
    public Projet findById(Integer id);
    public void deleteById(Integer id);
    public List<Projet> findByidencadrant(Integer idencadrant);
    public Projet updateTache(Integer id,Projet projet);

    public List<Projet> findByidstagiaire(Integer idstagiaire);
    public List<Stagiaire> findByidprojet(Integer idprojet);

}
