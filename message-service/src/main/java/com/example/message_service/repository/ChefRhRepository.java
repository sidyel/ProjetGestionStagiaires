package com.example.message_service.repository;

// Dépôt ChefRhRepository.java

import com.example.message_service.entity.ChefRh;
import com.example.message_service.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChefRhRepository extends JpaRepository<ChefRh, Long> {

    List<ChefRh> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

}
