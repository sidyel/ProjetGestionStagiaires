package com.example.message_service.repository;

// Dépôt StagiaireRepository.java

import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    List<Stagiaire> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
    List<Stagiaire> findByEncadrant(Encadrant encadrant);



}
