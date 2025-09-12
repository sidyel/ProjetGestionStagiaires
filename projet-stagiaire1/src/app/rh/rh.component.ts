declare var bootstrap: any;


import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ChefRh} from "../model/ChefRh";
import {RhService} from "../services/rh-service.service";


@Component({
  selector: 'app-rh',
  templateUrl: './rh.component.html',
  styleUrls: ['./rh.component.css']
})
export class RhComponent implements OnInit {

  formulaireRh!: FormGroup;
  listeRh: ChefRh[] = [];
  rhSelectionne: ChefRh | null = null;


  enModeModification: boolean = false;

  constructor(private fb: FormBuilder, private rhService: RhService) {}

  ngOnInit(): void {
    this.initialiserFormulaire();
    this.chargerTousLesRh();
  }

  initialiserFormulaire(): void {
    this.formulaireRh = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      datenaiss: ['', Validators.required],
      lieunaiss: [''],
      login: [''],
      motpasse: [''],
      telephonne: [''],
      email: ['', [Validators.required, Validators.email]],
      matricule: [''],
      cin: [''],
      etats: ['']
    });
  }

  chargerTousLesRh(): void {
    this.rhService.getAllRh().subscribe({
      next: data => this.listeRh = data,
      error: err => console.error('Erreur chargement RH:', err)
    });
  }

  ajouterRh(): void {
    if (this.formulaireRh.invalid) {
      this.formulaireRh.markAllAsTouched();
      return;
    }

    const dataRh: ChefRh = this.formulaireRh.value;

    if (this.enModeModification && this.rhSelectionne) {
      // 🔁 Mode modification
      this.rhService.modifierRh(this.rhSelectionne.idpersonne!, dataRh).subscribe({
        next: rhModifie => {
          const index = this.listeRh.findIndex(r => r.idpersonne === rhModifie.idpersonne);
          if (index !== -1) this.listeRh[index] = rhModifie;
          this.resetFormulaire();
          this.fermerModal('ajouterRhModal');
        },
        error: err => console.error('Erreur modification RH:', err)
      });
    } else {
      // ➕ Mode ajout
      this.rhService.ajouterRh(dataRh).subscribe({
        next: rhAjoute => {
          this.listeRh.push(rhAjoute);
          this.resetFormulaire();
          this.fermerModal('ajouterRhModal');
        },
        error: err => console.error('Erreur ajout RH:', err)
      });
    }
  }


  modifierRh(rh: ChefRh): void {
    this.rhSelectionne = rh;
    this.enModeModification = true;
    this.formulaireRh.patchValue(rh);
  }

  resetFormulaire(): void {
    this.formulaireRh.reset();
    this.enModeModification = false;
    this.rhSelectionne = null;
  }


  enregistrerModification(rh: ChefRh): void {
    if (!this.rhSelectionne) return;

    const rhModifie = this.formulaireRh.value;
    this.rhService.modifierRh(this.rhSelectionne.idpersonne!, rhModifie).subscribe({
      next: data => {
        const index = this.listeRh.findIndex(r => r.idpersonne === data.idpersonne);
        if (index !== -1) this.listeRh[index] = data;
        this.rhSelectionne = null;
        this.formulaireRh.reset();
        this.fermerModal('modifierRhModal');
      },
      error: err => console.error('Erreur modification RH:', err)
    });
  }

  supprimerRh(rh: ChefRh): void {
    if (!confirm(`Voulez-vous vraiment supprimer ${rh.nom} ${rh.prenom} ?`)) return;

    this.rhService.supprimerRh(rh.idpersonne!).subscribe({
      next: () => {
        this.listeRh = this.listeRh.filter(r => r.idpersonne !== rh.idpersonne);
      },
      error: err => console.error('Erreur suppression RH:', err)
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
