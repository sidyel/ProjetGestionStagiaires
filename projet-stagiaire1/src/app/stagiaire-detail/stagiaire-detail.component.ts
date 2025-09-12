import { Component, OnInit } from '@angular/core';
import { StagiaireService }    from '../services/stagiaire.service';
import { Stagiaire }           from '../model/Stagiaire';
import { Encadrant }           from '../model/encadrant';
import {EncadrantService} from "../services/encadrant-service.service";
import {AuthService} from "../services/auth.service";
declare var bootstrap: any;

@Component({
  selector: 'app-stagiaire-detail',
  templateUrl: './stagiaire-detail.component.html',
  styleUrls: ['./stagiaire-detail.component.css']
})
export class StagiaireDetailComponent implements OnInit {
  stagiaire?: Stagiaire;
  encadrant?: Encadrant;

  constructor(
    private stagService: StagiaireService,
    private encService: EncadrantService,
    private auth: AuthService
  ) {}

  ngOnInit(): void {
    const id = this.auth.idpersonne;
    this.stagService.getStagiaireParId(id).subscribe({
      next: s => {
        this.stagiaire = s;
        if (s.encadrant?.idpersonne) {
          this.encService.getEncadrantParId(s.encadrant.idpersonne).subscribe({
            next: e => this.encadrant = e,
            error: () => console.warn('Pas d’encadrant pour ce stagiaire')
          });
        }
      },
      error: err => console.error('Erreur chargement stagiaire :', err)
    });
  }

  ouvrirModal(id: string) {
    const el = document.getElementById(id);
    if (el) new bootstrap.Modal(el).show();
  }
}
