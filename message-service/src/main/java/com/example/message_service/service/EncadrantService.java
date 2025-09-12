package com.example.message_service.service;

// Service EncadrantService.java

import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.repository.EncadrantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EncadrantService {

    @Autowired
    private EncadrantRepository encadrantRepository;

    public Encadrant saveEncadrant(Encadrant encadrant) {
        return encadrantRepository.save(encadrant);
    }

    public Optional<Encadrant> getEncadrantById(Long id) {
        return encadrantRepository.findById(id);
    }

    public List<Encadrant> getAllEncadrants() {
        return encadrantRepository.findAll();
    }

    public List<Encadrant> rechercherEncadrant(String motClé) {
        return encadrantRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(motClé, motClé);
    }
    public void deleteEncadrant(Long id) {
        encadrantRepository.deleteById(id);
    }
}

