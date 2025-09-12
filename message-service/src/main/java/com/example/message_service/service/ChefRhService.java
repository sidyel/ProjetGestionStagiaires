package com.example.message_service.service;

// Service ChefRhService.java


import com.example.message_service.entity.ChefRh;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.repository.ChefRhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefRhService {

    @Autowired
    private ChefRhRepository chefRhRepository;

    public ChefRh saveChefRh(ChefRh chefRh) {
        return chefRhRepository.save(chefRh);
    }

    public List<ChefRh> rechercherChehrh(String motClé) {
        return chefRhRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(motClé, motClé);
    }

    public Optional<ChefRh> getChefRhById(Long id) {
        return chefRhRepository.findById(id);
    }

    public List<ChefRh> getAllChefRhs() {
        return chefRhRepository.findAll();
    }

    public void deleteChefRh(Long id) {
        chefRhRepository.deleteById(id);
    }
}

