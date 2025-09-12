import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Projet } from '../model/Projet';
import { Stagiaire } from '../model/Stagiaire';

@Injectable({
  providedIn: 'root'
})
export class ProjetService {

  private apiUrl = 'http://localhost:8081/projets';  // 🔁 adapte le port si nécessaire

  constructor(private http: HttpClient) {}

  // ➕ Ajouter un projet
  public ajouterProjet(projet: Projet): Observable<Projet> {
    return this.http.post<Projet>(this.apiUrl, projet);
  }

  // 📥 Récupérer tous les projets
  public getTousLesProjets(): Observable<Projet[]> {
    return this.http.get<Projet[]>(this.apiUrl);
  }

  // 🔍 Récupérer un projet par ID
  public getProjetParId(id: number): Observable<Projet> {
    return this.http.get<Projet>(`${this.apiUrl}/${id}`);
  }

  // ✏️ Modifier un projet
  public modifierProjet(id: number, projet: Projet): Observable<Projet> {
    return this.http.put<Projet>(`${this.apiUrl}/${id}`, projet);
  }

  // 🗑️ Supprimer un projet
  public supprimerProjet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // 📂 Obtenir les projets d’un encadrant
  public getProjetsParEncadrant(idencadrant: number): Observable<Projet[]> {
    return this.http.get<Projet[]>(`${this.apiUrl}/encadrant/${idencadrant}`);
  }

  // 📂 Obtenir les projets d’un stagiaire
  public getProjetsParStagiaire(idstagiaire: number): Observable<Projet[]> {
    return this.http.get<Projet[]>(`${this.apiUrl}/stagiaire/${idstagiaire}`);
  }

  // 👥 Obtenir les stagiaires associés à un projet
  public getStagiairesParProjet(idprojet: number): Observable<Stagiaire[]> {
    return this.http.get<Stagiaire[]>(`${this.apiUrl}/stagiaires/${idprojet}`);
  }
}
