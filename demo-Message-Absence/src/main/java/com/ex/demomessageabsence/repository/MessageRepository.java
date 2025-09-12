package com.ex.demomessageabsence.repository;

import com.ex.demomessageabsence.entite.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByIdStagiaire(Integer idStagiaire);
    List<Message> findByIdEncarant(Integer idEncarant);
    List<Message> findByIdStagiaireAndIdEncarant(Integer idStagiaire, Integer idEncarant);
}
