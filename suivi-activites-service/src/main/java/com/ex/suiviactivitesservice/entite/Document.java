package com.ex.suiviactivitesservice.entite;

import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity

public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddoc;

    private String type;
    private LocalDate date;
    private Integer idstagiaire;
    private Integer idtache;
    private String nomFichier;
    private String url;

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    @Transient
    private Tache tache;
    @Transient
    private Stagiaire stagiaire;


    public Document() {

    }

    public Document(Integer iddoc, String type, LocalDate date, Integer idstagiaire, Integer idtache) {
        this.iddoc = iddoc;
        this.type = type;
        this.date = date;
        this.idstagiaire = idstagiaire;
        this.idtache = idtache;
    }

    public Integer getIddoc() {
        return iddoc;
    }

    public void setIddoc(Integer iddoc) {
        this.iddoc = iddoc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getIdstagiaire() {
        return idstagiaire;
    }

    public void setIdstagiaire(Integer idstagiaire) {
        this.idstagiaire = idstagiaire;
    }

    public Integer getIdtache() {
        return idtache;
    }

    public void setIdtache(Integer idtache) {
        this.idtache = idtache;
    }
}

