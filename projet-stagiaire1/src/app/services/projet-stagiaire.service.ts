import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProjetStagiaireService {

  private apiUrl = 'http://localhost:8081/projets';

  constructor(private http: HttpClient) {}

  // … tes méthodes CRUD existantes …

  // 🚀 Assigner un stagiaire
  assignerStagiaire(idprojet: number, idstagiaire: number): Observable<any> {
    return this.http.post<any>(
      `${this.apiUrl}/${idprojet}/stagiaires/${idstagiaire}`, {}
    );
  }

  // 📑 Lister affectations d’un projet
  getAffectations(idprojet: number): Observable<{ id: number; idstagiaire: number }[]> {
    return this.http.get<{ id: number; idstagiaire: number }[]>(
      `${this.apiUrl}/${idprojet}/stagiaires`
    );
  }

  // 🗑️ Désaffecter
  desassignerAffectation(idps: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/*/stagiaires/${idps}`);
  }
}
