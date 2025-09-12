import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  constructor(private auth:AuthService,) {
  }

  nom = this.auth.nom;
  prenom = this.auth.prenom;
}
