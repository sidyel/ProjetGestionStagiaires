package com.ex.suiviactivitesservice.repository;

import com.ex.suiviactivitesservice.entite.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Integer> {

    List<Projet> findByidencadrant(Integer idencadrant);


}
