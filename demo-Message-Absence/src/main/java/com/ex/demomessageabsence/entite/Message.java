package com.ex.demomessageabsence.entite;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idStagiaire;
    private Long idEncarant;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnvoi;

    public Message() {}

    public Message(Long senderId, Long receiverId, String contenu, LocalDate dateEnvoi) {
        this.idEncarant = senderId;
        this.idStagiaire = receiverId;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    public Long getIdStagiaire() {
        return idStagiaire;
    }

    public void setIdStagiaire(Long idStagiaire) {
        this.idStagiaire = idStagiaire;
    }

    public Long getIdEncarant() {
        return idEncarant;
    }

    public void setIdEncarant(Long idEncarant) {
        this.idEncarant = idEncarant;
    }
// Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDate getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDate dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
}
