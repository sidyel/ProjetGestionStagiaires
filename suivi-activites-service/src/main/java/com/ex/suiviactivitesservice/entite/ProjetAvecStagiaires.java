package com.ex.suiviactivitesservice.entite;

import com.ex.suiviactivitesservice.objet.Stagiaire;

import java.util.List;

public class ProjetAvecStagiaires {
    private Projet projet;
    private List<Stagiaire> stagiaires;

    public ProjetAvecStagiaires(Projet p, List<Stagiaire> s) {
        this.projet = p;
        this.stagiaires = s;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public List<Stagiaire> getStagiaires() {
        return stagiaires;
    }

    public void setStagiaires(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }

    // Getters et Setters
}
