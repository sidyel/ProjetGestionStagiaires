package com.ex.suiviactivitesservice.entite;

import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idprojet;

    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer idencadrant;


    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }

    @Transient
    private Encadrant encadrant;

    public Projet(Integer idprojet, String titre, String description, LocalDate dateDebut, LocalDate dateFin, Integer idencadrant) {
        this.idprojet = idprojet;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idencadrant = idencadrant;
    }

    public Projet() {

    }

    public Integer getIdprojet() {
        return idprojet;
    }

    public void setIdprojet(Integer idprojet) {
        this.idprojet = idprojet;
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

    public Integer getIdencadrant() {
        return idencadrant;
    }

    public void setIdencadrant(Integer idencadrant) {
        this.idencadrant = idencadrant;
    }
}
