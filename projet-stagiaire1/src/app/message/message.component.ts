import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Message } from '../model/message';
import { MessageService } from '../services/message.service';
import { StagiaireService } from '../services/stagiaire.service';
import { EncadrantService } from '../services/encadrant-service.service';
import { Stagiaire } from '../model/Stagiaire';
import { Encadrant } from '../model/encadrant';

declare var bootstrap: any;

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  messages: Message[] = [];
  messageForm!: FormGroup;
  enModification = false;
  messageSelectionne: Message | null = null;

  listeStagiaires: Stagiaire[] = [];
  listeEncadrants: Encadrant[] = [];

  constructor(
    private fb: FormBuilder,
    private messageService: MessageService,
    private stagiaireService: StagiaireService,
    private encadrantService: EncadrantService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.chargerMessages();
    this.chargerStagiaires();
    this.chargerEncadrants();
  }

  initForm(): void {
    this.messageForm = this.fb.group({
      idStagiaire: [null, Validators.required],
      idEncadrant: [null, Validators.required],
      contenu: ['', Validators.required],
      dateEnvoi: [''],
    });
  }

  chargerMessages(): void {
    this.messageService.getTousLesMessages().subscribe({
      next: data => this.messages = data,
      error: err => console.error('Erreur chargement messages:', err)
    });
  }

  chargerStagiaires(): void {
    this.stagiaireService.getTousLesStagiaires().subscribe({
      next: data => this.listeStagiaires = data,
      error: err => console.error('Erreur chargement stagiaires:', err)
    });
  }

  chargerEncadrants(): void {
    this.encadrantService.getTousLesEncadrants().subscribe({
      next: data => this.listeEncadrants = data,
      error: err => console.error('Erreur chargement encadrants:', err)
    });
  }

  envoyerMessage(): void {
    if (this.messageForm.invalid) {
      this.messageForm.markAllAsTouched();
      return;
    }

    const message: Message = this.messageForm.value;

    if (this.enModification && this.messageSelectionne) {
      // Pas d'update backend prévu dans ton controller => à adapter si nécessaire
    } else {
      this.messageService.envoyerMessage(message).subscribe({
        next: newMsg => {
          this.messages.push(newMsg);
          this.resetForm();
          this.fermerModal('modalMessage');
        },
        error: err => console.error('Erreur ajout message :', err)
      });
    }
  }

  supprimerMessage(id: number): void {
    if (!confirm("Supprimer ce message ?")) return;

    this.messageService.supprimerMessage(id).subscribe({
      next: () => this.messages = this.messages.filter(m => m.id !== id),
      error: err => console.error('Erreur suppression :', err)
    });
  }

  resetForm(): void {
    this.messageForm.reset();
    this.enModification = false;
    this.messageSelectionne = null;
  }

  ouvrirModalAjout(): void {
    this.resetForm();
    const modal = new bootstrap.Modal(document.getElementById('modalMessage'));
    modal.show();
  }

  fermerModal(id: string): void {
    const modalElement = document.getElementById(id);
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
      modal.hide();
    }
  }
}
