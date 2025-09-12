package com.example.message_service.entity;

// Entité Admin.java
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn; // Import de l'annotation


@Entity

@PrimaryKeyJoinColumn(name = "idpersonne") // Spécifie la colonne de jointure avec la table Personne
public class Admin extends Personne {
    // idpersonne est hérité de Personne et est aussi la clé primaire de cette table
    // Pas besoin de idadmin car idpersonne est utilisé comme clé primaire et étrangère


    public Admin() {
    }
}
