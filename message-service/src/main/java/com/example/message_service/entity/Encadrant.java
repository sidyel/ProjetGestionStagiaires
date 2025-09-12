package com.example.message_service.entity;

// Entité Encadrant.java

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn; // Import de l'annotation

import java.util.Date;


@Entity

@PrimaryKeyJoinColumn(name = "idpersonne") // Spécifie la colonne de jointure avec la table Personne
public class Encadrant extends Personne {
    // idpersonne est hérité de Personne et est aussi la clé primaire de cette table
    private String specialite;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Encadrant(Long idpersonne, String prenom, String nom, Date datenaiss, String lieunaiss, String login, String motpasse, String telephonne, String email, String matricule, String cin, String etats,String role, String specialite) {
        super(idpersonne, prenom, nom, datenaiss, lieunaiss, login, motpasse, telephonne, email, matricule, cin, etats,role);
        this.specialite = specialite;
    }

    public Encadrant(String specialite) {
        this.specialite = specialite;
    }

    public Encadrant() {

    }
}
