package com.ex.suiviactivitesservice.web;

import com.ex.suiviactivitesservice.entite.Document;
import com.ex.suiviactivitesservice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.ex.suiviactivitesservice.entite.Document;
import com.ex.suiviactivitesservice.service.DocumentService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     *  Génère et enregistre l’attestation, puis renvoie la méta‑info.
     */
    @PostMapping("/attestation/{idStagiaire}")
    public ResponseEntity<Document> generate(
            @PathVariable Integer idStagiaire) {
        Document d = documentService.createAttestation(idStagiaire);
        return ResponseEntity.ok(d);
    }

    /**
     *  Sert physiquement le PDF (pour le bouton "Télécharger").
     */
    @GetMapping("/attestation/{idStagiaire}/pdf")
    public ResponseEntity<FileSystemResource> downloadPdf(
            @PathVariable Integer idStagiaire) {
        // on récupère la méta
        Document d = documentService
                .getByStagiaire(idStagiaire)
                .stream().filter(doc -> "attestation".equals(doc.getType()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pas d'attestation trouvée"));

        FileSystemResource resource =
                new FileSystemResource("uploads/attestations/" + d.getNomFichier());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + d.getNomFichier() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    /**
     * Liste des documents (méta‑info) d’un stagiaire.
     */
    @GetMapping("/stagiaire/{idStagiaire}")
    public List<Document> listByStagiaire(@PathVariable Integer idStagiaire) {
        return documentService.getByStagiaire(idStagiaire);
    }
}