import {AuthService} from "../services/auth.service";

declare var bootstrap: any;


import {Component, OnInit} from '@angular/core';
import {Tache} from "../model/Tache";
import {TacheService} from "../services/tache.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Stagiaire} from "../model/Stagiaire";
import {StagiaireService} from "../services/stagiaire.service";

@Component({
  selector: 'app-mes-tache',
  templateUrl: './mes-tache.component.html',
  styleUrl: './mes-tache.component.css'
})
export class MesTacheComponent implements OnInit {
  formulaire!: FormGroup;
  mesTaches: Tache[] = [];
  mesStagiaires: Stagiaire[] = [];
  enModification = false;
  tacheSelectionnee: Tache | null = null;

  constructor(
    private auth:AuthService,
    private fb: FormBuilder,
    private tacheService: TacheService,
    private stagiaireService: StagiaireService
  ) {}

  // Récupère l'ID de l'encadrant depuis le token stocké
  get encadrantId(): number {
    return this.auth.idpersonne;
  }

  ngOnInit(): void {
    this.initForm();
    this.chargerStagiaires();
    this.chargerTaches();
  }

  initForm(): void {
    this.formulaire = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      statut: ['', Validators.required],
      idstagiaire: [null, Validators.required]
    });
  }

  chargerTaches(): void {
    this.tacheService.getTachesParEncadrant(this.encadrantId).subscribe({
      next: data => this.mesTaches = data,
      error: err => console.error('Erreur chargement tâches :', err)
    });
  }

  chargerStagiaires(): void {
    this.stagiaireService.mesStagiaire(this.encadrantId).subscribe({
      next: data => this.mesStagiaires = data,
      error: err => console.error('Erreur chargement stagiaires :', err)
    });
  }

  getNomStagiaire(id: number): string {
    const stag = this.mesStagiaires.find(s => s.idpersonne === id);
    return stag ? `${stag.nom} ${stag.prenom}` : 'Inconnu';
  }

  ajouterOuModifier(): void {
    if (this.formulaire.invalid) {
      this.formulaire.markAllAsTouched();
      return;
    }

    const tache: Tache = {
      ...this.formulaire.value,
      idencadrant: this.encadrantId
    };

    if (this.enModification && this.tacheSelectionnee) {
      this.tacheService.modifierTache(this.tacheSelectionnee.idtache!, tache).subscribe({
        next: updated => {
          const idx = this.mesTaches.findIndex(t => t.idtache === updated.idtache);
          if (idx !== -1) this.mesTaches[idx] = updated;
          this.reset();
        },
        error: err => console.error('Erreur modif :', err)
      });
    } else {
      this.tacheService.ajouterTache(tache).subscribe({
        next: created => {
          this.mesTaches.push(created);
          this.reset();
        },
        error: err => console.error('Erreur ajout :', err)
      });
    }
  }

  modifier(tache: Tache): void {
    this.tacheSelectionnee = tache;
    this.enModification = true;
    this.formulaire.patchValue(tache);
    this.ouvrirModal('modalTache');
  }

  supprimer(id: number): void {
    if (!confirm('Confirmer suppression ?')) return;
    this.tacheService.supprimerTache(id).subscribe({
      next: () => this.mesTaches = this.mesTaches.filter(t => t.idtache !== id),
      error: err => console.error('Erreur suppression :', err)
    });
  }

  ouvrirModal(id: string): void {
    const el = document.getElementById(id);
    if (el) new bootstrap.Modal(el).show();
  }

  reset(): void {
    this.formulaire.reset();
    this.enModification = false;
    this.tacheSelectionnee = null;
    this.fermerModal('modalTache');
  }

  fermerModal(id: string): void {
    const el = document.getElementById(id);
    if (el) bootstrap.Modal.getInstance(el)?.hide();
  }
}
