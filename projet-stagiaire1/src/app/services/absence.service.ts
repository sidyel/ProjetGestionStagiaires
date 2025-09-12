import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Absence } from '../model/absence';

@Injectable({
  providedIn: 'root'
})
export class AbsenceService {

  private apiUrl = 'http://localhost:8083/absences';  // adapte le port si nécessaire

  constructor(private http: HttpClient) {}

  // ➕ Ajouter une absence
  public ajouterAbsence(absence: Absence): Observable<Absence> {
    return this.http.post<Absence>(this.apiUrl, absence);
  }

  // 📑 Récupérer toutes les absences
  public getToutesLesAbsences(): Observable<Absence[]> {
    return this.http.get<Absence[]>(this.apiUrl);
  }

  // 🔍 Récupérer une absence par ID
  public getAbsenceParId(id: number): Observable<Absence> {
    return this.http.get<Absence>(`${this.apiUrl}/${id}`);
  }

  // 📂 Récupérer les absences d’un stagiaire
  public getAbsencesParStagiaire(idStagiaire: number): Observable<Absence[]> {
    return this.http.get<Absence[]>(`${this.apiUrl}/stagiaire/${idStagiaire}`);
  }

  // ✏️ Modifier une absence
  public modifierAbsence(id: number, absence: Absence): Observable<Absence> {
    return this.http.put<Absence>(`${this.apiUrl}/${id}`, absence);
  }

  // 🗑️ Supprimer une absence
  public supprimerAbsence(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
