// src/app/projets/assign-stagiaires/assign-stagiaires.component.ts
import { Component, OnInit, Input } from '@angular/core';
import { ProjetService } from '../../services/projet.service';
import { StagiaireService } from '../../services/stagiaire.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ProjetStagiaireService} from "../../services/projet-stagiaire.service";
import {StudentWithProjects} from "../../model/StudentWithProjects";
import {Affectation} from "../../model/Affectation";

@Component({
  selector: 'app-assign-stagiaires',
  templateUrl: './assign-stagiaires.component.html'
})
export class AssignStagiairesComponent implements OnInit {
  idEncadrant = 3;  // à généraliser plus tard
  data: StudentWithProjects[] = [];

  constructor(
    private stagiaireSvc: StagiaireService,
    private projetSvc: ProjetService,
    private psSvc: ProjetStagiaireService
  ) {}

  ngOnInit(): void {
    // 1) récupérer les étudiants de l’encadrant
    this.stagiaireSvc.mesStagiaire(this.idEncadrant).subscribe(stags => {
      // pour chacun, on initialisera son entrée
      stags.forEach(s => {
        const entry: StudentWithProjects = {
          stagiaire: s,
          projets: [],
          affectations: []
        };
        this.data.push(entry);
        // puis charger ses affectations
        this.psSvc.getAffectations(s.idpersonne!).subscribe(affs => {
          entry.affectations = affs;
          // pour chaque affectation, charger le projet complet
          affs.forEach(a => {
            this.projetSvc.getProjetParId(a.id!).subscribe(p => {
              entry.projets.push(p);
            });
          });
        });
      });
    });
  }

  // Désaffecter un étudiant d’un projet
  desaffecter(entry: StudentWithProjects, affect: Affectation) {
    this.psSvc.desassignerAffectation(affect.id).subscribe(() => {
      // mettre à jour localement
      entry.affectations = entry.affectations.filter(a => a.id !== affect.id);
      entry.projets = entry.projets.filter(p => p.idprojet !== affect.idprojet);
    });
  }
}
