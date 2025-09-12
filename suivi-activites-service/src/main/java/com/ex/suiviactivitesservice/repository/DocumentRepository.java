package com.ex.suiviactivitesservice.repository;

import com.ex.suiviactivitesservice.entite.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByIdstagiaire(Integer idstagiaire);
}
