import { Component, OnInit } from '@angular/core';
import { Tache } from '../model/Tache';
import { TacheService } from '../services/tache.service';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-tache-stagiaire',
  templateUrl: './tache-stagiaire.component.html',
  styleUrls: ['./tache-stagiaire.component.css']
})
export class TacheStagiaireComponent implements OnInit {

  listeTaches: Tache[] = [];

  constructor(private tacheService: TacheService,private auth:AuthService) { }

  stagiaireId = this.auth.idpersonne;           // 🔒 temporaire, remplacer par l’ID du stagiaire connecté

  ngOnInit(): void {
    this.chargerTaches();
  }

  chargerTaches(): void {
    this.tacheService.getTachesParStagiaire(this.stagiaireId).subscribe({
      next: data => this.listeTaches = data,
      error: err => console.error('Erreur chargement tâches stagiaire :', err)
    });
  }

  basculerStatut(t: Tache): void {
    // simple bascule EN_COURS ↔ TERMINEE
    const nouveauStatut = t.statut === 'EN_COURS' ? 'TERMINEE' : 'EN_COURS';
    const maj: Tache = { ...t, statut: nouveauStatut };
    this.tacheService.modifierTache(t.idtache!, maj).subscribe({
      next: updated => {
        // mettre à jour localement
        const idx = this.listeTaches.findIndex(x => x.idtache === updated.idtache);
        if (idx !== -1) this.listeTaches[idx] = updated;
      },
      error: err => console.error('Erreur mise à jour statut :', err)
    });
  }

}
