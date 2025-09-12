import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Projet} from "../model/Projet";
import {Encadrant} from "../model/encadrant";
import {ProjetService} from "../services/projet.service";
import {EncadrantService} from "../services/encadrant-service.service";

declare var bootstrap: any;

@Component({
  selector: 'app-projet',
  templateUrl: './projet.component.html',
  styleUrls: ['./projet.component.css']
})
export class ProjetComponent implements OnInit {

  formulaireProjet!: FormGroup;
  listeProjets: Projet[] = [];
  projetSelectionne: Projet | null = null;
  enModification: boolean = false;

  listeEncadrants: Encadrant[] = [];

  constructor(
    private fb: FormBuilder,
    private projetService: ProjetService,
    private encadrantService: EncadrantService
  ) {}

  ngOnInit(): void {
    this.initialiserFormulaire();
    this.chargerProjets();
    this.chargerEncadrants();
  }

  initialiserFormulaire(): void {
    this.formulaireProjet = this.fb.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      idencadrant: [null, Validators.required]
    });
  }

  chargerProjets(): void {
    this.projetService.getTousLesProjets().subscribe({
      next: data => this.listeProjets = data,
      error: err => console.error('Erreur chargement projets :', err)
    });
  }

  chargerEncadrants(): void {
    this.encadrantService.getTousLesEncadrants().subscribe({
      next: data => this.listeEncadrants = data,
      error: err => console.error('Erreur chargement encadrants :', err)
    });
  }

  ajouterOuModifierProjet(): void {
    if (this.formulaireProjet.invalid) {
      this.formulaireProjet.markAllAsTouched();
      return;
    }

    const projetData: Projet = this.formulaireProjet.value;

    if (this.enModification && this.projetSelectionne) {
      this.projetService.modifierProjet(this.projetSelectionne.idprojet!, projetData).subscribe({
        next: updated => {
          const index = this.listeProjets.findIndex(p => p.idprojet === updated.idprojet);
          if (index !== -1) this.listeProjets[index] = updated;
          this.resetFormulaire();
          this.fermerModal('modalProjet');
        },
        error: err => console.error('Erreur modification projet :', err)
      });
    } else {
      this.projetService.ajouterProjet(projetData).subscribe({
        next: created => {
          this.listeProjets.push(created);
          this.resetFormulaire();
          this.fermerModal('modalProjet');
        },
        error: err => console.error('Erreur ajout projet :', err)
      });
    }
  }

  modifierProjet(projet: Projet): void {
    this.projetSelectionne = projet;
    this.enModification = true;
    this.formulaireProjet.patchValue(projet);
    const modal = new bootstrap.Modal(document.getElementById('modalProjet'));
    modal.show();
  }

  supprimerProjet(id: number): void {
    if (!confirm('Confirmer la suppression de ce projet ?')) return;

    this.projetService.supprimerProjet(id).subscribe({
      next: () => this.listeProjets = this.listeProjets.filter(p => p.idprojet !== id),
      error: err => console.error('Erreur suppression projet :', err)
    });
  }

  resetFormulaire(): void {
    this.formulaireProjet.reset();
    this.projetSelectionne = null;
    this.enModification = false;
  }

  fermerModal(id: string): void {
    const modalElement = document.getElementById(id);
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement) || new bootstrap.Modal(modalElement);
      modal.hide();
    }
  }
}
