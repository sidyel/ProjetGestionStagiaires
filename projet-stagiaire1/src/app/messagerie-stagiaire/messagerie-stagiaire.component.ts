import { Component, OnInit } from '@angular/core';
import { MessageService } from '../services/message.service';
import { Message } from '../model/message';
import {AuthService} from "../services/auth.service";
import {StagiaireService} from "../services/stagiaire.service";

@Component({
  selector: 'app-messagerie-stagiaire',
  templateUrl: './messagerie-stagiaire.component.html',
  styleUrls: ['./messagerie-stagiaire.component.css']
})
export class MessagerieStagiaireComponent implements OnInit {

  nouveauMessage: string = '';

  messages: Message[] = [];
  idStagiaire = this.auth.idpersonne;           // 🔒 temporaire, remplacer par l’ID du stagiaire connecté

  idEncadrant =3; // Son encadrant, remplace par l'id réel

  constructor(private messageService: MessageService,private stagiaireService:StagiaireService,private auth:AuthService) {}

  ngOnInit(): void {
    this.messageService.getMessagesEntre(this.idStagiaire, this.idEncadrant)
      .subscribe(data => this.messages = data);

    this.chargerEncadrant()
  }

  chargerMessages(): void {
    this.messageService.getMessagesParStagiaire(this.idStagiaire).subscribe(msgs => {
      this.messages = msgs;
      if (msgs.length > 0) {
        this.idEncadrant = msgs[0].idEncarant;
      }
    });
  }
  chargerEncadrant() {
    this.stagiaireService
      .getEncadrantOfStagiaire(this.idStagiaire)
      .subscribe({
        next: encadrant => {
          // ici tu récupères l’objet Encadrant complet
          this.idEncadrant = encadrant.idpersonne!;
        },
        error: err => console.error('Impossible de charger l’encadrant', err)
      });
  }

  envoyer(): void {
    if (!this.nouveauMessage.trim()) return;
    const message: Message = {
      idStagiaire: this.idStagiaire,
      idEncarant: this.idEncadrant,
      contenu: this.nouveauMessage,
      dateEnvoi: new Date().toISOString().slice(0, 10),
      encadrant: {} as any,
      stagiaire: {} as any
    };

    this.messageService.envoyerMessage(message).subscribe(msg => {
      this.messages.push(msg);
      this.nouveauMessage = '';
    });
  }
}
