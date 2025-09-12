package com.example.message_service.service;

// Service StagiaireService.java


import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.repository.EncadrantRepository;
import com.example.message_service.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private EncadrantRepository encadrantRepository;

    public Stagiaire saveStagiaire(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }

    public List<Stagiaire> getStagiairesByEncadrant(Long idEncadrant) {
        Encadrant encadrant=encadrantRepository.findById(idEncadrant).get();
        return stagiaireRepository.findByEncadrant(encadrant);
    }

    public List<Stagiaire> rechercherStagiaires(String motClé) {
        return stagiaireRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(motClé, motClé);
    }

    public Optional<Stagiaire> getStagiaireById(Long id) {
        return stagiaireRepository.findById(id);
    }

    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }

    public void deleteStagiaire(Long id) {
        stagiaireRepository.deleteById(id);
    }


}

