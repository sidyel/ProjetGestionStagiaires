package com.ex.suiviactivitesservice.web;

import com.ex.suiviactivitesservice.entite.Document;
import com.ex.suiviactivitesservice.feignClient.EncadrantFeignClient;
import com.ex.suiviactivitesservice.objet.Personne;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import com.ex.suiviactivitesservice.repository.DocumentRepository;
import com.ex.suiviactivitesservice.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.time.LocalDate;

@RestController
@RequestMapping("/attestation")
public class AttestationController {

    /*@Autowired
    private PdfService pdfService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private EncadrantFeignClient stagiaireService; // tu dois créer ou injecter ce service

    @PostMapping("/{idStagiaire}")
    public ResponseEntity<?> genererAttestation(@PathVariable Integer idStagiaire) {
        Stagiaire stagiaire = stagiaireService.getStagiaire(idStagiaire);
        if (stagiaire == null) return ResponseEntity.notFound().build();

        try {
            String nomFichier = "attestation_" + stagiaire.getIdpersonne()  + ".pdf";
            String chemin = "uploads/attestations/" + nomFichier;
            new File("uploads/attestations/").mkdirs(); // Crée le dossier si non existant

            Stagiaire personne=new Stagiaire();
            personne.setNom("sidy");
            personne.setPrenom("dione");
            pdfService.generateAttestationPdf(personne, chemin);

            Document document = new Document();
            document.setDate(LocalDate.now());
            document.setType("attestation");
            document.setIdstagiaire(idStagiaire);
            document.setNomFichier(nomFichier);
            document.setUrl("/uploads/attestations/" + nomFichier);
            documentRepository.save(document);

            return ResponseEntity.ok(document);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la génération du PDF : " + e.getMessage());
        }
    }*/
}
