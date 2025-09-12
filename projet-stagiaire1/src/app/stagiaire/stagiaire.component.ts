import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Stagiaire } from '../model/Stagiaire';
import { StagiaireService } from '../services/stagiaire.service';
import { Encadrant } from '../model/encadrant';
import {EncadrantService} from "../services/encadrant-service.service";
import {DocumentMeta, DocumentService} from "../sevices/document.service";

declare var bootstrap: any;

@Component({
  selector: 'app-stagiaire',
  templateUrl: './stagiaire.component.html',
  styleUrls: ['./stagiaire.component.css']
})
export class StagiaireComponent implements OnInit {

  formulaireStagiaire!: FormGroup;
  listeStagiaires: Stagiaire[] = [];
  listeEncadrants: Encadrant[] = [];
  motCleRecherche: string = ''; // champ lié au input de recherche

  stagiaireSelectionne: Stagiaire | null = null;
  enModeModification: boolean = false;

  constructor(
    private fb: FormBuilder,
    private stagiaireService: StagiaireService,
    private encadrantService: EncadrantService,
    private documentService: DocumentService

  ) {}

  ngOnInit(): void {
    this.initialiserFormulaire();
    this.chargerStagiaires();
    this.chargerEncadrants();
  }

  private initialiserFormulaire(): void {
    this.formulaireStagiaire = this.fb.group({
      nom:       ['', Validators.required],
      prenom:    ['', Validators.required],
      email:     ['', [Validators.required, Validators.email]],
      telephonne:[''],
      idencadrant:['', Validators.required]
      // tu peux ajouter d'autres champs ici si besoin...
    });
  }

  private chargerStagiaires(): void {
    this.stagiaireService.getTousLesStagiaires().subscribe({
      next: data => this.listeStagiaires = data,
      error: err => console.error('Erreur chargement stagiaires :', err)
    });
  }

  rechercherStagiaires(): void {
    if (!this.motCleRecherche.trim()) {
      this.chargerStagiaires(); // recharge la liste complète si vide
      return;
    }

    this.stagiaireService.rechercherStagiaires(this.motCleRecherche).subscribe({
      next: data => this.listeStagiaires = data,
      error: err => console.error('Erreur recherche stagiaires :', err)
    });
  }


  private chargerEncadrants(): void {
    this.encadrantService.getTousLesEncadrants().subscribe({
      next: data => this.listeEncadrants = data,
      error: err => console.error('Erreur chargement encadrants :', err)
    });
  }

  ajouterStagiaire(): void {
    if (this.formulaireStagiaire.invalid) {
      this.formulaireStagiaire.markAllAsTouched();
      return;
    }

    // ✅ Récupération des valeurs du formulaire
    const formValue = this.formulaireStagiaire.value;

    const encadrantChoisi = this.listeEncadrants.find(e => e.idpersonne === formValue.idencadrant);

    // 🔁 Création d'un objet Stagiaire à partir du formulaire
    const dto: Stagiaire = {
      nom: formValue.nom,
      prenom: formValue.prenom,
      email: formValue.email,
      telephonne: formValue.telephonne,
      encadrant: encadrantChoisi!,
      datenaiss: '',
      lieunaiss: '',
      login: '',
      motpasse: '',
      matricule: '',
      cin: '',
      etats: '',
      niveauEtude: '',
      specialite: '',
      etablissementOrigine: '',
      dateDebut: '',
      dateFin: '',
      statut: '',
      domaineStage: '',
      idencadrant: formValue.idencadrant
    };

    if (this.enModeModification && this.stagiaireSelectionne) {
      this.stagiaireService.modifierStagiaire(this.stagiaireSelectionne.idpersonne!, dto)
        .subscribe({
          next: updated => {
            const i = this.listeStagiaires.findIndex(s => s.idpersonne === updated.idpersonne);
            if (i !== -1) this.listeStagiaires[i] = updated;
            this.reinitialiserFormulaire();
            this.fermerModal('modalStagiaire');
          },
          error: err => console.error('Erreur modification :', err)
        });
    } else {
      this.stagiaireService.ajouterStagiaire(dto)
        .subscribe({
          next: created => {
            this.listeStagiaires.push(created);
            this.reinitialiserFormulaire();
            this.fermerModal('modalStagiaire');
          },
          error: err => console.error('Erreur ajout :', err)
        });
    }
  }

  genererAttestation(stag: Stagiaire): void {
    if (!confirm(`Générer l’attestation pour ${stag.nom} ${stag.prenom} ?`)) {
      return;
    }
    this.documentService.generateAttestation(stag.idpersonne!)
      .subscribe({
        next: (docMeta: DocumentMeta) => {
          // une fois la méta renvoyée, récupère le PDF binaire
          this.documentService.downloadPdf(stag.idpersonne!)
            .subscribe(blob => {
              const url = URL.createObjectURL(blob);
              const a = document.createElement('a');
              a.href = url;
              a.download = docMeta.nomFichier;
              a.click();
              URL.revokeObjectURL(url);
            }, err => console.error('Erreur téléchargement PDF :', err));
        },
        error: err => console.error('Erreur génération attestation :', err)
      });
  }

  getNomPrenomEncadrant(id: number): string {
    const enc = this.listeEncadrants.find(e => e.idpersonne === id);
    return enc ? `${enc.nom} ${enc.prenom}` : '';
  }

  modifierStagiaire(stag: Stagiaire): void {
    this.stagiaireSelectionne = stag;
    this.enModeModification = true;
    this.formulaireStagiaire.patchValue(stag);
    // Ouvre le modal
    const modalEl = document.getElementById('modalStagiaire');
    new bootstrap.Modal(modalEl).show();
  }

  supprimerStagiaire(stag: Stagiaire): void {
    if (!confirm(`Supprimer ${stag.nom} ?`)) return;
    this.stagiaireService.supprimerStagiaire(stag.idpersonne!)
      .subscribe({
        next: () => this.listeStagiaires =
          this.listeStagiaires.filter(s => s.idpersonne !== stag.idpersonne),
        error: err => console.error('Erreur suppression :', err)
      });
  }

  private reinitialiserFormulaire(): void {
    this.formulaireStagiaire.reset();
    this.stagiaireSelectionne = null;
    this.enModeModification = false;
  }

  fermerModal(id: string): void {
    const modalEl = document.getElementById(id);
    const modal = bootstrap.Modal.getInstance(modalEl);
    modal?.hide();
  }
}
