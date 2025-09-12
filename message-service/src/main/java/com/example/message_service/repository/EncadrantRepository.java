package com.example.message_service.repository;

// Dépôt EncadrantRepository.java

import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {

    List<Encadrant> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

}
