package com.ex.suiviactivitesservice.entite;

import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity

public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtache;

    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;
    private Integer idstagiaire;
    private Integer idencadrant;
    private Integer idprojet;

    @Transient
    private Stagiaire stagiaire;

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    @Transient
    private Projet projet;
    @Transient
    private Encadrant encadrant;

    public Tache(Integer idtache, String titre, String description, LocalDate dateDebut,
                 LocalDate dateFin, String statut, Integer idstagiaire, Integer idencadrant,
                 Integer idprojet) {
        this.idtache = idtache;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.idstagiaire = idstagiaire;
        this.idencadrant = idencadrant;
        this.idprojet = idprojet;
    }

    public Tache() {

    }

    public Integer getIdtache() {
        return idtache;
    }

    public void setIdtache(Integer idtache) {
        this.idtache = idtache;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getIdstagiaire() {
        return idstagiaire;
    }

    public void setIdstagiaire(Integer idstagiaire) {
        this.idstagiaire = idstagiaire;
    }

    public Integer getIdencadrant() {
        return idencadrant;
    }

    public void setIdencadrant(Integer idencadrant) {
        this.idencadrant = idencadrant;
    }

    public Integer getIdprojet() {
        return idprojet;
    }

    public void setIdprojet(Integer idprojet) {
        this.idprojet = idprojet;
    }
}
