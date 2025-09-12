package com.ex.suiviactivitesservice.repository;

import com.ex.suiviactivitesservice.entite.Projet;
import com.ex.suiviactivitesservice.entite.ProjetStagiaire;
import com.ex.suiviactivitesservice.objet.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjetStagiairerepo extends JpaRepository<ProjetStagiaire, Long> {
    List<ProjetStagiaire> findByIdprojet(Integer idprojet);
    List<ProjetStagiaire> findByIdstagiaire(Integer idstagiaire);

    @Query(value = "SELECT * FROM stagiaire s " +
            "WHERE s.idpersonne IN (SELECT ps.idstagiaire FROM projet_stagiaire ps WHERE ps.idprojet = :idprojet)",
            nativeQuery = true)
    List<Stagiaire> findStagiairesByProjet(@Param("idprojet") Integer idprojet);
}

