import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Absence } from '../model/absence';
import { AbsenceService } from '../services/absence.service';
import { StagiaireService } from '../services/stagiaire.service';
import { Stagiaire } from '../model/Stagiaire';

declare var bootstrap: any;

@Component({
  selector: 'app-absence',
  templateUrl: './absence.component.html',
  styleUrls: ['./absence.component.css']
})
export class AbsenceComponent implements OnInit {

  formulaireAbsence!: FormGroup;
  listeAbsences: Absence[] = [];
  listeStagiaires: Stagiaire[] = [];
  absenceSelectionnee: Absence | null = null;
  enModification: boolean = false;

  constructor(
    private fb: FormBuilder,
    private absenceService: AbsenceService,
    private stagiaireService: StagiaireService
  ) {}

  ngOnInit(): void {
    this.initialiserFormulaire();
    this.chargerStagiaires();
    this.chargerAbsences();
  }

  initialiserFormulaire(): void {
    this.formulaireAbsence = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      motif: ['', Validators.required],
      justification: [''],
      idstagiaire: [null, Validators.required]
    });
  }

  chargerStagiaires(): void {
    this.stagiaireService.getTousLesStagiaires().subscribe({
      next: data => this.listeStagiaires = data,
      error: err => console.error('Erreur chargement stagiaires :', err)
    });
  }

  chargerAbsences(): void {
    this.absenceService.getToutesLesAbsences().subscribe({
      next: data => this.listeAbsences = data,
      error: err => console.error('Erreur chargement absences :', err)
    });
  }

  getNomStagiaire(id: number | undefined): string {
    if (id == null) return '';
    const stag = this.listeStagiaires.find(s => s.idpersonne === id);
    return stag ? `${stag.nom} ${stag.prenom}` : 'Inconnu';
  }

  ajouterOuModifierAbsence(): void {
    if (this.formulaireAbsence.invalid) {
      this.formulaireAbsence.markAllAsTouched();
      return;
    }

    const data: Absence = this.formulaireAbsence.value;

    if (this.enModification && this.absenceSelectionnee) {
      this.absenceService.modifierAbsence(this.absenceSelectionnee.idabsence!, data).subscribe({
        next: updated => {
          const i = this.listeAbsences.findIndex(a => a.idabsence === updated.idabsence);
          if (i !== -1) this.listeAbsences[i] = updated;
          this.resetFormulaire();
          this.fermerModal('modalAbsence');
        },
        error: err => console.error('Erreur modification absence :', err)
      });
    } else {
      this.absenceService.ajouterAbsence(data).subscribe({
        next: created => {
          this.listeAbsences.push(created);
          this.resetFormulaire();
          this.fermerModal('modalAbsence');
        },
        error: err => console.error('Erreur ajout absence :', err)
      });
    }
  }

  modifierAbsence(absence: Absence): void {
    this.absenceSelectionnee = absence;
    this.enModification = true;
    this.formulaireAbsence.patchValue(absence);
    this.ouvrirModal('modalAbsence');
  }

  supprimerAbsence(id: number): void {
    if (!confirm('Confirmer la suppression ?')) return;
    this.absenceService.supprimerAbsence(id).subscribe({
      next: () => this.listeAbsences = this.listeAbsences.filter(a => a.idabsence !== id),
      error: err => console.error('Erreur suppression absence :', err)
    });
  }

  resetFormulaire(): void {
    this.formulaireAbsence.reset();
    this.enModification = false;
    this.absenceSelectionnee = null;
  }

  ouvrirModal(id: string): void {
    const modalEl = document.getElementById(id);
    if (modalEl) new bootstrap.Modal(modalEl).show();
  }

  fermerModal(id: string): void {
    const modalEl = document.getElementById(id);
    if (modalEl) {
      const modal = bootstrap.Modal.getInstance(modalEl) || new bootstrap.Modal(modalEl);
      modal.hide();
    }
  }
}
