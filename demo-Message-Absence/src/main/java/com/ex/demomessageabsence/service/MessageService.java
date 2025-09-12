package com.ex.demomessageabsence.service;


import com.ex.demomessageabsence.entite.Message;
import com.ex.demomessageabsence.feign.EncadrantFeignClient;
import com.ex.demomessageabsence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MessageService implements MessageInterface {


    private final MessageRepository repository;

    @Autowired
    private EncadrantFeignClient encadrantFeignClient;

    public MessageService(MessageRepository repository)
    {
        this.repository = repository;
    }

    public Message envoyerMessage(Message message)
    {
        message.setDateEnvoi(LocalDate.now());
        return repository.save(message);
    }

    @Override
    public List<Message> getMessagesByStagiaire(Integer idStagiaire) {

        return repository.findByIdStagiaire(idStagiaire);
    }

    @Override
    public List<Message> getMessagesByEncadrant(Integer idEncarant) {
        return repository.findByIdEncarant(idEncarant);
    }

    @Override
    public List<Message> getMessagesBetween(Integer idStagiaire, Integer idEncarant) {
        return repository.findByIdStagiaireAndIdEncarant(idStagiaire, idEncarant);
    }

    @Override
    public Message getMessageById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteMessage(Integer id) {
        Message message = repository.findById(id).get();
        repository.delete(message);
    }

    public List<Message> findAll() {
        return repository.findAll();
    }


}
