import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Projet } from '../model/Projet';
import { ProjetService } from '../services/projet.service';
import { Stagiaire } from '../model/Stagiaire';
import { StagiaireService } from '../services/stagiaire.service';
import {ProjetStagiaireService} from "../services/projet-stagiaire.service";
import {AuthService} from "../services/auth.service";

declare var bootstrap: any;

@Component({
  selector: 'app-mes-projet',
  templateUrl: './mes-projet.component.html',
  styleUrls: ['./mes-projet.component.css']
})
export class MesProjetComponent implements OnInit {
  projets: Projet[] = [];
  projetForm!: FormGroup;
  enModification = false;
  projetSelectionne: Projet | null = null;

  stagiaires: Stagiaire[] = [];
  assignForm!: FormGroup;
  projetPourAssign: Projet | null = null;

  constructor(
    private fb: FormBuilder,
    private projetService: ProjetService,
    private stagiaireService: StagiaireService,
    private psService: ProjetStagiaireService,
    private authService: AuthService
  ) {}

  // Récupère l'ID de l'encadrant depuis le token stocké
  get idEncadrant(): number {
    return this.authService.idpersonne;
  }

  ngOnInit(): void {
    this.initForm();
    this.initAssignForm();
    this.chargerMesProjets();
    this.chargerStagiaires();
  }

  initForm(): void {
    this.projetForm = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      idencadrant: [this.idEncadrant]
    });
  }

  chargerMesProjets(): void {
    this.projetService.getProjetsParEncadrant(this.idEncadrant)
      .subscribe({
        next: data => this.projets = data,
        error: err => console.error('Erreur chargement projets :', err)
      });
  }

  ouvrirAjout(): void {
    this.enModification = false;
    this.projetSelectionne = null;
    this.projetForm.reset({ idencadrant: this.idEncadrant });
    this.ouvrirModal('modalProjet');
  }

  soumettreProjet(): void {
    const data = this.projetForm.value as Projet;
    if (this.enModification && this.projetSelectionne) {
      this.projetService.modifierProjet(this.projetSelectionne.idprojet!, data)
        .subscribe({
          next: () => {
            this.chargerMesProjets();
            this.fermerModal('modalProjet');
          },
          error: err => console.error('Erreur modification :', err)
        });
    } else {
      this.projetService.ajouterProjet(data)
        .subscribe({
          next: () => {
            this.chargerMesProjets();
            this.fermerModal('modalProjet');
          },
          error: err => console.error('Erreur ajout :', err)
        });
    }
  }

  modifierProjet(projet: Projet): void {
    this.enModification = true;
    this.projetSelectionne = projet;
    this.projetForm.patchValue({
      titre: projet.titre,
      description: projet.description,
      dateDebut: projet.dateDebut,
      dateFin: projet.dateFin,
      idencadrant: projet.idencadrant
    });
    this.ouvrirModal('modalProjet');
  }

  supprimerProjet(id: number): void {
    if (!confirm('Supprimer ce projet ?')) return;
    this.projetService.supprimerProjet(id)
      .subscribe({
        next: () => this.chargerMesProjets(),
        error: err => console.error('Erreur suppression :', err)
      });
  }

  ouvrirModal(id: string): void {
    const modalEl = document.getElementById(id);
    if (modalEl) new bootstrap.Modal(modalEl).show();
  }
  fermerModal(id: string): void {
    const modalEl = document.getElementById(id);
    if (modalEl) {
      const m = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
      m.hide();
    }
  }

  chargerStagiaires(): void {
    this.stagiaireService.getTousLesStagiaires()
      .subscribe({
        next: data => this.stagiaires = data,
        error: err => console.error('Erreur chargement stagiaires :', err)
      });
  }

  initAssignForm(): void {
    this.assignForm = this.fb.group({ idstagiaire: [null, Validators.required] });
  }

  ouvrirAssignModal(projet: Projet): void {
    this.projetPourAssign = projet;
    this.assignForm.reset();
    this.ouvrirModal('modalAssign');
  }

  assigner(): void {
    if (!this.projetPourAssign || this.assignForm.invalid) return;
    const idP = this.projetPourAssign.idprojet!;
    const idS = this.assignForm.value.idstagiaire;
    this.psService.assignerStagiaire(idP, idS)
      .subscribe({
        next: () => this.fermerModal('modalAssign'),
        error: err => console.error('Erreur assignation :', err)
      });
  }
}
