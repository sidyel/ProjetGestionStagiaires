package com.example.message_service.entity;

// Entité ChefRh.java

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn; // Import de l'annotation


@Entity

@PrimaryKeyJoinColumn(name = "idpersonne") // Spécifie la colonne de jointure avec la table Personne
public class ChefRh extends Personne {
    // idpersonne est hérité de Personne et est aussi la clé primaire de cette table
    // Pas besoin de idchef car idpersonne est utilisé comme clé primaire et étrangère

    public ChefRh() {
    }
}
