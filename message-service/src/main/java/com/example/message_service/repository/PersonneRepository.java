package com.example.message_service.repository;

// Dépôt PersonneRepository.java

import com.example.message_service.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    // Des méthodes personnalisées peuvent être ajoutées ici si nécessaire
    Optional<Personne> findByLogin(String login);

}
