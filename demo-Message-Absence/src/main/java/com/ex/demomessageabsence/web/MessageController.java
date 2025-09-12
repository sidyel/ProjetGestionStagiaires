package com.ex.demomessageabsence.web;

import com.ex.demomessageabsence.entite.Message;
import com.ex.demomessageabsence.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    // ✅ Envoi d'un message
    @PostMapping
    public Message envoyerMessage(@RequestBody Message message) {
        return service.envoyerMessage(message);
    }

    // ✅ Récupérer tous les messages
    @GetMapping
    public List<Message> messages() {
        return service.findAll();
    }

    // ✅ Récupérer un message par ID
    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Integer id) {
        return service.getMessageById(id);
    }

    // ✅ Récupérer tous les messages d’un stagiaire
    @GetMapping("/stagiaire/{idStagiaire}")
    public List<Message> getMessagesStagiaire(@PathVariable Integer idStagiaire) {
        return service.getMessagesByStagiaire(idStagiaire);
    }

    // ✅ Récupérer tous les messages reçus par un encadrant
    @GetMapping("/encadrant/{idEncadrant}")
    public List<Message> getMessagesRecus(@PathVariable Integer idEncadrant) {
        return service.getMessagesByEncadrant(idEncadrant);
    }

    // ✅ Récupérer les messages échangés entre un stagiaire et un encadrant
    @GetMapping("/stagiaire-encadrant/{idStagiaire}/{idEncadrant}")
    public List<Message> messageList(@PathVariable Integer idStagiaire, @PathVariable Integer idEncadrant) {
        return service.getMessagesBetween(idStagiaire, idEncadrant);
    }

    // (facultatif) Suppression d'un message
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        service.deleteMessage(id);
    }
}
