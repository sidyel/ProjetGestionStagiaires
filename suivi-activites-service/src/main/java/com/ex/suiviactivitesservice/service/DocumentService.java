package com.ex.suiviactivitesservice.service;

import com.ex.suiviactivitesservice.entite.Document;
import com.ex.suiviactivitesservice.feignClient.EncadrantFeignClient;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import com.ex.suiviactivitesservice.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentService {

    @Autowired private DocumentRepository docRepo;
    @Autowired private EncadrantFeignClient stagClient;
    @Autowired private PdfService pdfService;

    /**
     * Génère l'attestation PDF, la stocke dans uploads/attestations,
     * enregistre la méta‑info dans la table Document et renvoie cette entité.
     */
    public Document createAttestation(Integer idStagiaire) {
        // 1) Récupérer le stagiaire distant
        Stagiaire stag = stagClient.getStagiaire(idStagiaire);

        // 2) Préparer le répertoire de stockage
        File dir = new File("uploads/attestations");
        if (!dir.exists()) dir.mkdirs();

        // 3) Construire le nom de fichier
        String fname = String.format("attestation_%d_%s_%s.pdf",
                stag.getIdpersonne(), stag.getNom(), stag.getPrenom());
        String fullPath = dir.getAbsolutePath() + File.separator + fname;

        // 4) Générer le PDF et l’écrire sur disque
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            byte[] pdfBytes = pdfService.createAttestationPdf(
                    stag.getNom(),
                    stag.getPrenom(),
                    Long.valueOf(stag.getIdpersonne()),
                    stag.getDateDebut().toString(),
                    stag.getDateFin().toString()
            );
            fos.write(pdfBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erreur génération PDF", e);
        }

        // 5) Enregistrer en base
        Document doc = new Document();
        doc.setType("attestation");
        doc.setDate(LocalDate.now());
        doc.setIdstagiaire(idStagiaire);
        doc.setNomFichier(fname);
        doc.setUrl("/uploads/attestations/" + fname);
        return docRepo.save(doc);
    }

    /**
     * Liste des documents pour un stagiaire.
     */
    public List<Document> getByStagiaire(Integer idStagiaire) {
        return docRepo.findByIdstagiaire(idStagiaire);
    }
}
