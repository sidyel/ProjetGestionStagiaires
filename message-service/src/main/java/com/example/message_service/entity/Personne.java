package com.example.message_service.entity;

// Entité Personne.java

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Stratégie d'héritage pour les sous-classes

public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation de l'ID
    private Long idpersonne;

    public Personne() {

    }

    public Long getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(Long idpersonne) {
        this.idpersonne = idpersonne;
    }

    public String getEtats() {
        return etats;
    }

    public Personne(Long idpersonne, String prenom, String nom, Date datenaiss, String lieunaiss,
                    String login, String motpasse, String telephonne, String email,
                    String matricule, String cin, String etats,String role) {
        this.idpersonne = idpersonne;
        this.prenom = prenom;
        this.nom = nom;
        this.datenaiss = datenaiss;
        this.lieunaiss = lieunaiss;
        this.login = login;
        this.motpasse = motpasse;
        this.telephonne = telephonne;
        this.email = email;
        this.matricule = matricule;
        this.cin = cin;
        this.etats = etats;
        this.role = role;
    }



    public void setEtats(String etats) {
        this.etats = etats;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephonne() {
        return telephonne;
    }

    public void setTelephonne(String telephonne) {
        this.telephonne = telephonne;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLieunaiss() {
        return lieunaiss;
    }

    public void setLieunaiss(String lieunaiss) {
        this.lieunaiss = lieunaiss;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private String nom;
    private String prenom;

    @Temporal(TemporalType.DATE) // Spécifie que c'est une date sans l'heure
    private Date datenaiss;

    private String lieunaiss;
    private String login;
    private String motpasse; // En production, il est recommandé de hacher les mots de passe
    private String telephonne;
    private String email;
    private String matricule;
    private String cin;
    private String etats; // État de la personne (actif, inactif, etc.)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
