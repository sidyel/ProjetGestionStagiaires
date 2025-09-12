import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Absence } from '../model/absence';
import { AbsenceService } from '../services/absence.service';

declare const bootstrap: any;

@Component({
  selector: 'app-absence-stagiaire',
  templateUrl: './absence-stagiaire.component.html',
  styleUrls: ['./absence-stagiaire.component.css']
})
export class AbsenceStagiaireComponent implements OnInit {

  formulaire!: FormGroup;
  listeAbsences: Absence[] = [];
  enEdition = false;
  absenceSelectionnee: Absence | null = null;

  readonly idStagiaire = 9; // à remplacer dynamiquement

  constructor(
    private fb: FormBuilder,
    private absenceService: AbsenceService
  ) {}

  ngOnInit(): void {
    this.initForm();
    this.chargerAbsences();
  }

  initForm(): void {
    this.formulaire = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin:   ['', Validators.required],
      motif:     ['', Validators.required],
      justification: ['', Validators.required]
    });
  }

  chargerAbsences(): void {
    this.absenceService.getAbsencesParStagiaire(this.idStagiaire).subscribe({
      next: abs => this.listeAbsences = abs,
      error: err => console.error('Erreur chargement absences', err)
    });
  }

  ouvrirModal(id: string, absence?: Absence): void {
    this.enEdition = !!absence;
    this.absenceSelectionnee = absence ?? null;
    if (absence) {
      this.formulaire.patchValue({
        dateDebut: absence.dateDebut,
        dateFin: absence.dateFin,
        motif: absence.motif,
        justification: absence.justification
      });
    } else {
      this.formulaire.reset();
    }
    const modalEl = document.getElementById(id);
    if (modalEl) new bootstrap.Modal(modalEl).show();
  }

  soumettre(): void {
    if (this.formulaire.invalid) {
      this.formulaire.markAllAsTouched();
      return;
    }

    const data = {
      ...this.formulaire.value,
      idstagiaire: this.idStagiaire
    } as Absence;

    if (this.enEdition && this.absenceSelectionnee) {
      // mise à jour de la justification (et évent. dates/motif)
      this.absenceService.modifierAbsence(this.absenceSelectionnee.idabsence!, data)
        .subscribe({
          next: updated => {
            const i = this.listeAbsences.findIndex(a => a.idabsence === updated.idabsence);
            if (i !== -1) this.listeAbsences[i] = updated;
            this.fermerModal('modalAbsence');
          },
          error: err => console.error('Erreur modification absence', err)
        });
    } else {
      // création
      this.absenceService.ajouterAbsence(data)
        .subscribe({
          next: created => {
            this.listeAbsences.push(created);
            this.fermerModal('modalAbsence');
          },
          error: err => console.error('Erreur ajout absence', err)
        });
    }
  }

  fermerModal(id: string): void {
    const modalEl = document.getElementById(id);
    const modal = modalEl && bootstrap.Modal.getInstance(modalEl);
    modal?.hide();
  }
}
