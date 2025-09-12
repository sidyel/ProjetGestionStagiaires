package com.ex.suiviactivitesservice;


import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.entite.ProjetStagiaire;
import com.ex.suiviactivitesservice.entite.Tache;
import com.ex.suiviactivitesservice.service.PojetServiceImpl;
import com.ex.suiviactivitesservice.service.ProjetStagiaireService;
import com.ex.suiviactivitesservice.service.TacheService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.entite.Tache;
import com.ex.suiviactivitesservice.service.PojetServiceImpl;
import com.ex.suiviactivitesservice.service.ProjetStagiaireService;
import com.ex.suiviactivitesservice.service.TacheService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            TacheService tacheService,
            PojetServiceImpl projetService,
            ProjetStagiaireService projetStagiaireService) {
        return args -> {
            // Supposons que vos encadrants existent de 1 à 5
            for (int encadrantId = 1; encadrantId <= 5; encadrantId++) {
                // Supposons que vos stagiaires existent de 6 à 10
                for (int stagiaireId = 6; stagiaireId <= 10; stagiaireId++) {

                    // 1) Création de 5 projets pour ce stagiaire sous cet encadrant
                    List<Projet> projetsCrees = new ArrayList<>();
                    for (int i = 1; i <= 5; i++) {
                        Projet projet = new Projet();
                        projet.setTitre("Projet " + i + " - Stagiaire " + stagiaireId);
                        projet.setDescription("Description du projet " + i);
                        projet.setDateDebut(LocalDate.now());
                        projet.setDateFin(LocalDate.now().plusMonths(1));
                        projet.setIdencadrant(encadrantId);
                        Projet savedProjet = projetService.save(projet);
                        projetsCrees.add(savedProjet);

                        // Affectation du projet au stagiaire
                        projetStagiaireService.assigner(savedProjet.getIdprojet(), stagiaireId);
                    }

                    // 2) Création de 5 tâches pour ce stagiaire, réparties sur les 5 projets
                    for (int j = 1; j <= 5; j++) {
                        Tache tache = new Tache();
                        tache.setTitre("Tâche " + j + " - Stagiaire " + stagiaireId);
                        tache.setDescription("Détail de la tâche " + j);
                        tache.setDateDebut(LocalDate.now());
                        tache.setDateFin(LocalDate.now().plusWeeks(2));
                        tache.setStatut("EN_COURS");
                        tache.setIdstagiaire(stagiaireId);
                        tache.setIdencadrant(encadrantId);
                        // On répartit les tâches sur les projets en round‑robin
                        Projet projetPourTache = projetsCrees.get((j - 1) % projetsCrees.size());
                        tache.setIdprojet(projetPourTache.getIdprojet());
                        tacheService.save(tache);
                    }
                }
            }
        };
    }
}
