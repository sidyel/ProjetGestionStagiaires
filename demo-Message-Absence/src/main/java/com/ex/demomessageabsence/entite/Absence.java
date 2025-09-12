package com.ex.demomessageabsence.entite;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idabsence;

    private Date dateDebut;
    private Date dateFin;
    private String motif;
    private String justification;

    private Integer idstagiaire; // À mapper manuellement avec le service Stagiaire

    @Transient
   private Stagiaire stagiaire;

    public Absence(Integer idabsence, Stagiaire stagiaire, Integer idstagiaire, String justification, String motif, Date dateFin, Date dateDebut) {
        this.idabsence = idabsence;
        this.stagiaire = stagiaire;
        this.idstagiaire = idstagiaire;
        this.justification = justification;
        this.motif = motif;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
    }

    public Absence() {
        
    }

    public Integer getIdabsence() {
        return idabsence;
    }

    public void setIdabsence(Integer idabsence) {
        this.idabsence = idabsence;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public Integer getIdstagiaire() {
        return idstagiaire;
    }

    public void setIdstagiaire(Integer idstagiaire) {
        this.idstagiaire = idstagiaire;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }
}

