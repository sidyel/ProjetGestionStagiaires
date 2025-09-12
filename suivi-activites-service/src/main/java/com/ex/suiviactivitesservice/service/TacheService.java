package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.entite.Tache;

import java.util.List;

public interface TacheService {

    public Tache save(Tache tache);
    public List<Tache> findAll();
    public Tache findById(Integer id);
    public void deleteById(Integer id);
    public List<Tache> findByidencadrant(Integer idencadrant);
    public List<Tache> findByidstagiaire(Integer idstagiaire);
    public Tache updateTache(Integer id,Tache tache);
    public List<Tache> findByidprojet(Integer idprojet);

}
