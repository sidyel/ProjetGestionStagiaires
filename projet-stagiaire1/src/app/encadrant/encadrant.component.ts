import {EncadrantService} from "../services/encadrant-service.service";

declare var bootstrap: any;

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Encadrant } from '../model/encadrant';
import {Stagiaire} from "../model/Stagiaire";
import {StagiaireService} from "../services/stagiaire.service";

@Component({
  selector: 'app-encadrant',
  templateUrl: './encadrant.component.html',
  styleUrls: ['./encadrant.component.css']
})
export class EncadrantComponent implements OnInit {

  formulaireEncadrant!: FormGroup;
  listeEncadrants: Encadrant[] = [];
  encadrantSelectionne: Encadrant | null = null;
  enModeModification: boolean = false;
  motCleRecherche: string = ''; // champ lié au input de recherche
  listeStagiaires: Stagiaire[] = [];


  constructor(private fb: FormBuilder, private encadrantService: EncadrantService,private stagiaireService: StagiaireService,) {}

  ngOnInit(): void {
    this.initialiserFormulaire();
    this.chargerTousLesEncadrants();
  }

  initialiserFormulaire(): void {
    this.formulaireEncadrant = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      datenaiss: [''],
      lieunaiss: [''],
      login: [''],
      motpasse: [''],
      telephonne: [''],
      email: ['', [Validators.required, Validators.email]],
      matricule: [''],
      cin: [''],
      etats: [''],
      specialite: ['']
    });
  }

  chargerTousLesEncadrants(): void {
    this.encadrantService.getTousLesEncadrants().subscribe({
      next: data => this.listeEncadrants = data,
      error: err => console.error('Erreur chargement encadrants:', err)
    });
  }

  ajouterEncadrant(): void {
    if (this.formulaireEncadrant.invalid) {
      this.formulaireEncadrant.markAllAsTouched();
      return;
    }

    const data: Encadrant = this.formulaireEncadrant.value;

    if (this.enModeModification && this.encadrantSelectionne) {
      this.encadrantService.modifierEncadrant(this.encadrantSelectionne.idpersonne!, data).subscribe({
        next: updated => {
          const index = this.listeEncadrants.findIndex(e => e.idpersonne === updated.idpersonne);
          if (index !== -1) this.listeEncadrants[index] = updated;
          this.resetFormulaire();
          this.fermerModal('ajouterEncadrantModal');
        },
        error: err => console.error('Erreur modification:', err)
      });
    } else {
      this.encadrantService.ajouterEncadrant(data).subscribe({
        next: enc => {
          this.listeEncadrants.push(enc);
          this.resetFormulaire();
          this.fermerModal('ajouterEncadrantModal');
        },
        error: err => console.error('Erreur ajout encadrant:', err)
      });
    }
  }

  modifierEncadrant(encadrant: Encadrant): void {
    this.encadrantSelectionne = encadrant;
    this.enModeModification = true;
    this.formulaireEncadrant.patchValue(encadrant);
  }

  supprimerEncadrant(encadrant: Encadrant): void {
    if (!confirm(`Supprimer ${encadrant.nom} ${encadrant.prenom} ?`)) return;
    this.encadrantService.supprimerEncadrant(encadrant.idpersonne!).subscribe({
      next: () => {
        this.listeEncadrants = this.listeEncadrants.filter(e => e.idpersonne !== encadrant.idpersonne);
      },
      error: err => console.error('Erreur suppression:', err)
    });
  }

  resetFormulaire(): void {
    this.formulaireEncadrant.reset();
    this.enModeModification = false;
    this.encadrantSelectionne = null;
  }

  rechercherEncadrant(): void {
    if (!this.motCleRecherche.trim()) {
      this.chargerTousLesEncadrants(); // recharge la liste complète si vide
      return;
    }

    this.encadrantService.rechercherEncadrant(this.motCleRecherche).subscribe({
      next: data => this.listeEncadrants = data,
      error: err => console.error('Erreur recherche stagiaires :', err)
    });
  }


  fermerModal(id: string): void {
    const modalElement = document.getElementById(id);
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
      modal.hide();
    }
  }
}
