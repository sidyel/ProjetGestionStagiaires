import {AuthService} from "../services/auth.service";

declare var bootstrap: any;

import { Component, OnInit } from '@angular/core';
import { StagiaireService } from '../services/stagiaire.service';
import { Stagiaire } from '../model/Stagiaire';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-mes-stagiaire',
  templateUrl: './mes-stagiaire.component.html',
  styleUrls: ['./mes-stagiaire.component.css']
})
export class MesStagiaireComponent implements OnInit {

  stagiaireSelectionne: Stagiaire | null = null;
  formulaireStagiaire!: FormGroup;
  enModeModification: boolean = false;
  stagiaireDetails: Stagiaire | null = null;


  stagiaires: Stagiaire[] = [];

  constructor(private stagiaireService: StagiaireService, private fb:FormBuilder,private autService:AuthService) {}

  ngOnInit(): void {
    this.formulaireStagiaire = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telephonne: [''],
      specialite: [''],
      niveauEtude: [''],
      statut: ['']
    });

    // Exemple : récupérer l'id de l'encadrant connecté depuis le localStorage
    const idEncadrant = this.autService.idpersonne;

    if (idEncadrant) {
      this.stagiaireService.mesStagiaire(idEncadrant).subscribe({
        next: (data) => {
          this.stagiaires = data;
          console.log('Stagiaires récupérés :', data);
        },
        error: (err) => {
          console.error('Erreur de chargement des stagiaires', err);
        }
      });
    } else {
      console.warn('Aucun encadrant connecté');
    }
  }

  afficherDetails(stagiaire: Stagiaire): void {
    this.stagiaireDetails = stagiaire;
    const modalEl = document.getElementById('modalDetailsStagiaire');
    if (modalEl) {
      new bootstrap.Modal(modalEl).show();
    }
  }


  modifierStagiaire(stagiaire: Stagiaire): void {
    this.stagiaireSelectionne = stagiaire;
    this.enModeModification = true;
    this.formulaireStagiaire.patchValue(stagiaire);

    const modal = new bootstrap.Modal(document.getElementById('modalStagiaire')!);
    modal.show();
  }

  soumettreModification(): void {
    if (this.formulaireStagiaire.invalid || !this.stagiaireSelectionne) return;

    const updated: Stagiaire = {
      ...this.stagiaireSelectionne,
      ...this.formulaireStagiaire.value
    };

    this.stagiaireService.modifierStagiaire(this.stagiaireSelectionne.idpersonne!, updated).subscribe({
      next: (result) => {
        const i = this.stagiaires.findIndex(s => s.idpersonne === result.idpersonne);
        if (i !== -1) this.stagiaires[i] = result;

        this.stagiaireSelectionne = null;
        this.enModeModification = false;
        this.formulaireStagiaire.reset();

        const modal = bootstrap.Modal.getInstance(document.getElementById('modalStagiaire')!);
        modal?.hide();
      },
      error: (err) => console.error('Erreur lors de la modification :', err)
    });
  }


}
