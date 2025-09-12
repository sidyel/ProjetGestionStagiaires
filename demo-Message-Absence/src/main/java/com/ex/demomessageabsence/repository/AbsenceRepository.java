package com.ex.demomessageabsence.repository;

import com.ex.demomessageabsence.entite.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Integer> {
    List<Absence> findByIdstagiaire(Integer idstagiaire);
}

