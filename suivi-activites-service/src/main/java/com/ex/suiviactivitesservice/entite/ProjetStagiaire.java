package com.ex.suiviactivitesservice.entite;

import com.ex.suiviactivitesservice.objet.Encadrant;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import jakarta.persistence.*;

@Entity

public class ProjetStagiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID technique
    private Integer idstagiaire;
    private Integer idprojet;

    @Transient
    private Stagiaire stagiaire;
    @Transient
    private Projet projet;


    public ProjetStagiaire(Long id, Integer idstagiaire, Integer idprojet) {
        this.id = id;
        this.idstagiaire = idstagiaire;
        this.idprojet = idprojet;
    }

    public ProjetStagiaire() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdprojet() {
        return idprojet;
    }

    public void setIdprojet(Integer idprojet) {
        this.idprojet = idprojet;
    }

    public Integer getIdstagiaire() {
        return idstagiaire;
    }

    public void setIdstagiaire(Integer idstagiaire) {
        this.idstagiaire = idstagiaire;
    }
}
