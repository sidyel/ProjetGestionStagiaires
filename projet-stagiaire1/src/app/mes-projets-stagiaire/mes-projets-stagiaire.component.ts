// src/app/mes-projets-stagiaire/mes-projets-stagiaire.component.ts
import { Component, OnInit } from '@angular/core';
import { Projet } from '../model/Projet';
import { ProjetService } from '../services/projet.service';

@Component({
  selector: 'app-mes-projets-stagiaire',
  templateUrl: './mes-projets-stagiaire.component.html',
  styleUrls: ['./mes-projets-stagiaire.component.css']
})
export class MesProjetsStagiaireComponent implements OnInit {

  projets: Projet[] = [];
  readonly idStagiaire = 9; // À récupérer dynamiquement plus tard

  constructor(private projetService: ProjetService) {}

  ngOnInit(): void {
    this.chargerProjets();
  }

  private chargerProjets(): void {
    this.projetService.getProjetsParStagiaire(this.idStagiaire).subscribe({
      next: projets => {
        this.projets = projets;
        // Optionnel : enrichir chaque projet avec son encadrant
        this.projets.forEach(p => {
          this.projetService.getProjetsParEncadrant(p.idprojet!).subscribe(enc => {
            p.encadrant = enc;
          });
        });
      },
      error: err => console.error('Erreur chargement projets :', err)
    });
  }

  /**
   * Retourne un libellé de badge selon l'avancement
   */
  getBadgeClass(p: Projet): string {
    if (p.avancement === undefined) return 'badge-secondary';
    if (p.avancement >= 80) return 'badge-success';
    if (p.avancement >= 50) return 'badge-warning';
    return 'badge-danger';
  }
}
