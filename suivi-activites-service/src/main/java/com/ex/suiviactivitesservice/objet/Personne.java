package com.ex.suiviactivitesservice.objet;

public class Personne {
    private Integer idpersonne;
    private String nom;
    private String prenom;
    private String datenaiss;
    private String lieunaiss;
    private String login;
    private String motpasse;
    private String telephonne;
    private String email;
    private String matricule;
    private String cin;
    private String etats;

    public Personne() {
    }

    public Personne(Integer idpersonne, String nom, String prenom, String datenaiss, String lieunaiss,
                    String login, String motpasse, String telephonne, String email, String matricule,
                    String cin, String etats) {
        this.idpersonne = idpersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaiss = datenaiss;
        this.lieunaiss = lieunaiss;
        this.login = login;
        this.motpasse = motpasse;
        this.telephonne = telephonne;
        this.email = email;
        this.matricule = matricule;
        this.cin = cin;
        this.etats = etats;
    }

    public Integer getIdpersonne() {
        return idpersonne;
    }

    public void setIdpersonne(Integer idpersonne) {
        this.idpersonne = idpersonne;
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

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
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
}
