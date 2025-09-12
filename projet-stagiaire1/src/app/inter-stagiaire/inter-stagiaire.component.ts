import { Component } from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-inter-stagiaire',
  templateUrl: './inter-stagiaire.component.html',
  styleUrl: './inter-stagiaire.component.css'
})
export class InterStagiaireComponent {

  isHome = false;
  constructor(private auth:AuthService,private router: Router) {
    this.router.events.subscribe(e => {
      if (e instanceof NavigationEnd) {
        this.isHome = e.urlAfterRedirects === '/interStagiaire';
      }
    });
  }
  stagiaireId = this.auth.idpersonne;           // 🔒 temporaire, remplacer par l’ID du stagiaire connecté
  nom = this.auth.nom;
  prenom = this.auth.prenom;


}
