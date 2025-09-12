package com.ex.suiviactivitesservice.repository;

import com.ex.suiviactivitesservice.entite.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacheRepository extends JpaRepository<Tache, Integer> {


    List<Tache> findByIdstagiaire(Integer idstagiaire);
    List<Tache> findByIdencadrant(Integer idencadrant);
    List<Tache> findByIdprojet(Integer idprojet);

}
