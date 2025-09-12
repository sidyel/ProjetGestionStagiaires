import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-inter-encadrant',
  templateUrl: './inter-encadrant.component.html',
  styleUrl: './inter-encadrant.component.css'
})
export class InterEncadrantComponent {
  constructor(private auth:AuthService,private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.initialiserProfilDepuisStorage();

  }

  nom = this.auth.nom;
  prenom = this.auth.prenom;

}
