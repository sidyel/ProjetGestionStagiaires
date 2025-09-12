package com.ex.suiviactivitesservice.objet;

import java.util.Date;

public class Encadrant {
    public Encadrant() {
    }

    public Encadrant(Long idpersonne, String prenom, String nom, Date datenaiss, String lieunaiss,
                     String login, String motpasse, String telephonne, String email, String matricule, String cin, String etats, String specialite) {
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
        this.specialite = specialite;
    }

    private Long idpersonne;
    private String prenom;
    private String nom;
    private Date datenaiss;
    private String lieunaiss;
    private String login;
    private String motpasse;
    private String telephonne;
    private String email;
    private String matricule;
    private String cin;

    public Long getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(Long idpersonne) {
        this.idpersonne = idpersonne;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getLieunaiss() {
        return lieunaiss;
    }

    public void setLieunaiss(String lieunaiss) {
        this.lieunaiss = lieunaiss;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotpasse() {
        return motpasse;
    }

    public void setMotpasse(String motpasse) {
        this.motpasse = motpasse;
    }

    public String getTelephonne() {
        return telephonne;
    }

    public void setTelephonne(String telephonne) {
        this.telephonne = telephonne;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getEtats() {
        return etats;
    }

    public void setEtats(String etats) {
        this.etats = etats;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    private String etats;
    private String specialite;

}
