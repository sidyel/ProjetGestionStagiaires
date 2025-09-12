package com.example.message_service;

import com.example.message_service.entity.ChefRh;
import com.example.message_service.entity.Encadrant;
import com.example.message_service.entity.Personne;
import com.example.message_service.entity.Stagiaire;
import com.example.message_service.repository.ChefRhRepository;
import com.example.message_service.repository.EncadrantRepository;
import com.example.message_service.repository.PersonneRepository;
import com.example.message_service.repository.StagiaireRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.IntStream;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            ChefRhRepository personneRepo,
            EncadrantRepository encadrantRepo,
            StagiaireRepository stagiaireRepo,
            PasswordEncoder encoder
    ) {
        return args -> {
            // --- Données réalistes ---
            String[] nomsEncadrants = {"Diop", "Fall", "Ndoye", "Ba", "Ndiaye"};
            String[] prenomsEncadrants = {"Mamadou", "Cheikh", "Abdoulaye", "Aliou", "Oumar"};

            String[] nomsStagiaires = {"Gueye", "Sow", "Sagna", "Diallo", "Faye"};
            String[] prenomsStagiaires = {"Aissatou", "Fatou", "Mariama", "Khadija", "Bineta"};

            String[] nomsRH = {"Cissé", "Sy", "Thiam", "Camara", "Mbaye"};
            String[] prenomsRH = {"Adama", "Pape", "Astou", "Ndeye", "Serigne"};

            // --- Ajouter Encadrants ---
            for (int i = 0; i < 5; i++) {
                Encadrant enc = new Encadrant();
                enc.setNom(nomsEncadrants[i]);
                enc.setPrenom(prenomsEncadrants[i]);
                enc.setLogin("enc" + (i + 1));
                enc.setMotpasse(encoder.encode("12345"));
                enc.setEmail("enc" + (i + 1) + "@mail.com");
                enc.setRole("ENCADRANT");
                enc.setDatenaiss(new Date());
                enc.setLieunaiss("Dakar");
                enc.setCin("ENC" + (i + 1));
                enc.setMatricule("E" + (i + 1));
                enc.setTelephonne("77000000" + (i + 1));
                enc.setEtats("ACTIF");
                enc.setSpecialite("Java");
                encadrantRepo.save(enc);
            }

            // --- Ajouter Stagiaires (1 par encadrant) ---
            for (int i = 0; i < 5; i++) {
                Encadrant encadrant = encadrantRepo.findAll().get(i);

                Stagiaire stagiaire = new Stagiaire();
                stagiaire.setNom(nomsStagiaires[i]);
                stagiaire.setPrenom(prenomsStagiaires[i]);
                stagiaire.setLogin("stag" + (i + 1));
                stagiaire.setMotpasse(encoder.encode("12345"));
                stagiaire.setEmail("stag" + (i + 1) + "@mail.com");
                stagiaire.setRole("STAGIAIRE");
                stagiaire.setDatenaiss(new Date());
                stagiaire.setLieunaiss("Thiès");
                stagiaire.setCin("STG" + (i + 1));
                stagiaire.setMatricule("S" + (i + 1));
                stagiaire.setTelephonne("78000000" + (i + 1));
                stagiaire.setEtats("ACTIF");
                stagiaire.setNiveauEtude("Master 2");
                stagiaire.setSpecialite("SIR");
                stagiaire.setEncadrant(encadrant);
                stagiaire.setStatut("En cours");
                stagiaire.setDateDebut(new Date());
                stagiaire.setDateFin(new Date());
                stagiaire.setDomaineStage("Informatique");
                stagiaire.setEtablissementOrigine("UCAD");
                stagiaireRepo.save(stagiaire);
            }

            // --- Ajouter RH ---
            for (int i = 0; i < 5; i++) {
                ChefRh rh = new ChefRh();
                rh.setNom(nomsRH[i]);
                rh.setPrenom(prenomsRH[i]);
                rh.setLogin("rh" + (i + 1));
                rh.setMotpasse(encoder.encode("12345"));
                rh.setEmail("rh" + (i + 1) + "@mail.com");
                rh.setRole("RH");
                rh.setDatenaiss(new Date());
                rh.setLieunaiss("Ziguinchor");
                rh.setCin("RH" + (i + 1));
                rh.setMatricule("R" + (i + 1));
                rh.setTelephonne("76000000" + (i + 1));
                rh.setEtats("ACTIF");
                personneRepo.save(rh);
            }

            System.out.println("✅ Données initialisées avec des personnes réelles sénégalaises !");
        };
    }

}
