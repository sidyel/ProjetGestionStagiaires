package com.ex.demomessageabsence.service;

import com.ex.demomessageabsence.entite.Absence;

import java.util.List;

public interface AbsenceInterface {
    Absence createAbsence(Absence absence);
    Absence getAbsenceById(Integer id);
    List<Absence> getAllAbsences();
    List<Absence> getAbsencesByStagiaire(Integer idStagiaire);
    Absence updateAbsence(Integer id, Absence updatedAbsence);
    void deleteAbsence(Integer id);
}

