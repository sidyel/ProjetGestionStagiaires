package com.example.message_service.entity;


// Entité Stagiaire.java

import jakarta.persistence.*;

import java.util.Date;

@Entity

@PrimaryKeyJoinColumn(name = "idpersonne") // Spécifie la colonne de jointure avec la table Personne
public class Stagiaire extends Personne {
    // idpersonne est hérité de Personne et est aussi la clé primaire de cette table

    @ManyToOne(fetch = FetchType.EAGER) // Un stagiaire a un seul encadrant
    @JoinColumn(name = "idencadrant") // Colonne de jointure pour l'encadrant
    private Encadrant encadrant;

    private String niveauEtude;
    private String specialite;
    private String etablissementOrigine;


    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private String statut;
    private String domaineStage;

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public Stagiaire() {
    }

    public Stagiaire(Encadrant encadrant, String statut, String domaineStage, Date dateFin,
                     Date dateDebut, String etablissementOrigine, String specialite,
                     String niveauEtude) {
        this.encadrant = encadrant;
        this.statut = statut;
        this.domaineStage = domaineStage;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.etablissementOrigine = etablissementOrigine;
        this.specialite = specialite;
        this.niveauEtude = niveauEtude;
    }



    public String getDomaineStage() {
        return domaineStage;
    }

    public void setDomaineStage(String domaineStage) {
        this.domaineStage = domaineStage;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getEtablissementOrigine() {
        return etablissementOrigine;
    }

    public void setEtablissementOrigine(String etablissementOrigine) {
        this.etablissementOrigine = etablissementOrigine;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }
}

