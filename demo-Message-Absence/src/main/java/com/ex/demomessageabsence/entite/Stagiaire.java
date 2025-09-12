package com.ex.demomessageabsence.entite;

public class Stagiaire {
    private Integer idstagiaire;
    private Integer idpersonne;
    private Integer idencadrant;
    private String niveauEtude;
    private String specialite;
    private String etablissementOrigine;
    private String dateDebut;
    private String dateFin;
    private String statut;
    private String domaineStage;

    public Stagiaire() {
    }

    public Stagiaire(Integer idstagiaire, Integer idpersonne, Integer idencadrant, String niveauEtude,
                     String specialite, String etablissementOrigine, String dateDebut, String dateFin,
                     String statut, String domaineStage) {
        this.idstagiaire = idstagiaire;
        this.idpersonne = idpersonne;
        this.idencadrant = idencadrant;
        this.niveauEtude = niveauEtude;
        this.specialite = specialite;
        this.etablissementOrigine = etablissementOrigine;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.domaineStage = domaineStage;
    }

    public Integer getIdstagiaire() {
        return idstagiaire;
    }

    public void setIdstagiaire(Integer idstagiaire) {
        this.idstagiaire = idstagiaire;
    }

    public Integer getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(Integer idpersonne) {
        this.idpersonne = idpersonne;
    }

    public Integer getIdencadrant() {
        return idencadrant;
    }

    public void setIdencadrant(Integer idencadrant) {
        this.idencadrant = idencadrant;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEtablissementOrigine() {
        return etablissementOrigine;
    }

    public void setEtablissementOrigine(String etablissementOrigine) {
        this.etablissementOrigine = etablissementOrigine;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDomaineStage() {
        return domaineStage;
    }

    public void setDomaineStage(String domaineStage) {
        this.domaineStage = domaineStage;
    }
}
