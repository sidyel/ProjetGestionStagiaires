import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Tache} from "../model/Tache";
import {TacheService} from "../services/tache.service";
import {StagiaireService} from "../services/stagiaire.service";
import {EncadrantService} from "../services/encadrant-service.service";
import {Stagiaire} from "../model/Stagiaire";
import {Encadrant} from "../model/encadrant";


declare var bootstrap: any;

@Component({
  selector: 'app-tache',
  templateUrl: './tache.component.html',
  styleUrls: ['./tache.component.css']
})
export class TacheComponent implements OnInit {

  formulaireTache!: FormGroup;
  listeTaches: Tache[] = [];
  tacheSelectionnee: Tache | null = null;
  enModification: boolean = false;
  listeStagiaires: Stagiaire[] = [];
  listeEncadrants: Encadrant[] = [];

  constructor(private fb: FormBuilder, private tacheService: TacheService,private stagService: StagiaireService,
              private encService: EncadrantService) {}

  ngOnInit(): void {
    this.initialiserFormulaire();
    this.chargerTaches();
    this.chargerStagiaires();
    this.chargerEncadrants();
  }

  initialiserFormulaire(): void {
    this.formulaireTache = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      statut: ['', Validators.required],
      idstagiaire: [null, Validators.required],
      idencadrant: [null, Validators.required],
      idprojet: [null],
    });
  }

  chargerTaches(): void {
    this.tacheService.getToutesLesTaches().subscribe({
      next: data => this.listeTaches = data,
      error: err => console.error('Erreur chargement tâches :', err)
    });
  }

  private chargerStagiaires() {
    this.stagService.getTousLesStagiaires().subscribe(
      list => this.listeStagiaires = list,
      err  => console.error(err)
    );
  }

  private chargerEncadrants() {
    this.encService.getTousLesEncadrants().subscribe(
      list => this.listeEncadrants = list,
      err  => console.error(err)
    );
  }

  getNomStagiaire(id: number): string {
    const s = this.listeStagiaires.find(x => x.idpersonne === id);
    return s ? `${s.prenom} ${s.nom}` : `#${id}`;
  }

  getNomEncadrant(id: number): string {
    const e = this.listeEncadrants.find(x => x.idpersonne === id);
    return e ? `${e.prenom} ${e.nom}` : `#${id}`;
  }

  ajouterOuModifierTache(): void {
    if (this.formulaireTache.invalid) {
      this.formulaireTache.markAllAsTouched();
      return;
    }

    const tacheData: Tache = this.formulaireTache.value;

    if (this.enModification && this.tacheSelectionnee) {
      // 🔁 Modification
      this.tacheService.modifierTache(this.tacheSelectionnee.idtache!, tacheData).subscribe({
        next: updated => {
          const index = this.listeTaches.findIndex(t => t.idtache === updated.idtache);
          if (index !== -1) this.listeTaches[index] = updated;
          this.resetFormulaire();
          this.fermerModal('modalTache');
        },
        error: err => console.error('Erreur modification tâche :', err)
      });
    } else {
      // ➕ Ajout
      this.tacheService.ajouterTache(tacheData).subscribe({
        next: created => {
          this.listeTaches.push(created);
          this.resetFormulaire();
          this.fermerModal('modalTache');
        },
        error: err => console.error('Erreur ajout tâche :', err)
      });
    }
  }

  modifierTache(tache: Tache): void {
    this.tacheSelectionnee = tache;
    this.enModification = true;
    this.formulaireTache.patchValue(tache);

    const modalEl = document.getElementById('modalTache');
    if (modalEl) new bootstrap.Modal(modalEl).show();
  }

  supprimerTache(idtache: number): void {
    if (!confirm('Voulez-vous vraiment supprimer cette tâche ?')) return;

    this.tacheService.supprimerTache(idtache).subscribe({
      next: () => {
        this.listeTaches = this.listeTaches.filter(t => t.idtache !== idtache);
      },
      error: err => console.error('Erreur suppression tâche :', err)
    });
  }

  resetFormulaire(): void {
    this.formulaireTache.reset();
    this.enModification = false;
    this.tacheSelectionnee = null;
  }

  fermerModal(id: string): void {
    const modalElement = document.getElementById(id);
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
      modal.hide();
    }
  }
}
