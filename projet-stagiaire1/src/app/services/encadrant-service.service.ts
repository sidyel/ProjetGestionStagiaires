
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Encadrant} from "../model/encadrant";
import {Stagiaire} from "../model/Stagiaire";

@Injectable({
  providedIn: 'root'
})
export class EncadrantService {

  private apiUrl = 'http://localhost:8080/encadrants';

  constructor(private http: HttpClient) {}

  public rechercherEncadrant(motCle: string): Observable<Encadrant[]> {
    return this.http.get<Encadrant[]>(`${this.apiUrl}/search?motCle=${motCle}`);
  }

  // ✅ Créer un encadrant
  ajouterEncadrant(encadrant: Encadrant): Observable<Encadrant> {
    return this.http.post<Encadrant>(this.apiUrl, encadrant);
  }

  // ✅ Récupérer tous les encadrants
  getTousLesEncadrants(): Observable<Encadrant[]> {
    return this.http.get<Encadrant[]>(this.apiUrl);
  }

  // ✅ Récupérer un encadrant par ID
  getEncadrantParId(id: number): Observable<Encadrant> {
    return this.http.get<Encadrant>(`${this.apiUrl}/${id}`);
  }

  // ✅ Modifier un encadrant
  modifierEncadrant(id: number, encadrant: Encadrant): Observable<Encadrant> {
    return this.http.put<Encadrant>(`${this.apiUrl}/${id}`, encadrant);
  }

  // ✅ Supprimer un encadrant
  supprimerEncadrant(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
