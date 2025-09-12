package com.example.message_service.service;

// Service PersonneService.java


import com.example.message_service.entity.Personne;
import com.example.message_service.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    // Créer ou mettre à jour une personne
    public Personne savePersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    // Récupérer une personne par son ID
    public Optional<Personne> getPersonneById(Long id) {
        return personneRepository.findById(id);
    }

    // Récupérer toutes les personnes
    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    // Supprimer une personne par son ID
    public void deletePersonne(Long id) {
        personneRepository.deleteById(id);
    }
}

