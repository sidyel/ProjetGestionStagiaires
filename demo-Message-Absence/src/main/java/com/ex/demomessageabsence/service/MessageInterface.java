package com.ex.demomessageabsence.service;


import com.ex.demomessageabsence.entite.Message;

import java.util.List;

public interface MessageInterface {

    Message envoyerMessage(Message message);
    List<Message> getMessagesByStagiaire(Integer idStagiaire);
    List<Message> getMessagesByEncadrant(Integer idEncarant);
    List<Message> getMessagesBetween(Integer idStagiaire, Integer idEncarant);
    Message getMessageById(Integer id);
    void deleteMessage(Integer id);
}
