import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tache } from '../model/Tache';

@Injectable({
  providedIn: 'root'
})
export class TacheService {

  private apiUrl = 'http://localhost:8081/taches';

  constructor(private http: HttpClient) {}

  // 🔹 Créer une tâche
  public ajouterTache(tache: Tache): Observable<Tache> {
    return this.http.post<Tache>(this.apiUrl, tache);
  }

  // 🔹 Obtenir toutes les tâches
  public getToutesLesTaches(): Observable<Tache[]> {
    return this.http.get<Tache[]>(this.apiUrl);
  }

  // 🔹 Récupérer une tâche par ID
  public getTacheParId(id: number): Observable<Tache> {
    return this.http.get<Tache>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Modifier une tâche
  public modifierTache(id: number, tache: Tache): Observable<Tache> {
    return this.http.put<Tache>(`${this.apiUrl}/${id}`, tache);
  }

  // 🔹 Supprimer une tâche
  public supprimerTache(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Tâches par ID d'encadrant
  public getTachesParEncadrant(idencadrant: number): Observable<Tache[]> {
    return this.http.get<Tache[]>(`${this.apiUrl}/encadrant/${idencadrant}`);
  }

  // 🔹 Tâches par ID de stagiaire
  public getTachesParStagiaire(idstagiaire: number): Observable<Tache[]> {
    return this.http.get<Tache[]>(`${this.apiUrl}/stagiaire/${idstagiaire}`);
  }

  // 🔹 Tâches par ID de projet
  public getTachesParProjet(idprojet: number): Observable<Tache[]> {
    return this.http.get<Tache[]>(`${this.apiUrl}/projet/${idprojet}`);
  }
}
