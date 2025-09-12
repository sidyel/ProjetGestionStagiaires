import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Stagiaire } from '../model/Stagiaire';
import {Encadrant} from "../model/encadrant";

@Injectable({
  providedIn: 'root'
})
export class StagiaireService {

  private apiUrl = 'http://localhost:8080/stagiaires';

  constructor(private http: HttpClient) {}

  // src/app/services/stagiaire.service.ts
  public rechercherStagiaires(motCle: string): Observable<Stagiaire[]> {
    return this.http.get<Stagiaire[]>(`${this.apiUrl}/search?motCle=${motCle}`);
  }

  public getEncadrantOfStagiaire(idStagiaire: number): Observable<Encadrant> {
    return this.http.get<Encadrant>(`${this.apiUrl}/${idStagiaire}/encadrant`);
  }


  // ➕ Ajouter un stagiaire
  public ajouterStagiaire(stagiaire: Stagiaire): Observable<Stagiaire> {
    return this.http.post<Stagiaire>(this.apiUrl, stagiaire);
  }

  // 📑 Récupérer tous les stagiaires
  public getTousLesStagiaires(): Observable<Stagiaire[]> {
    return this.http.get<Stagiaire[]>(this.apiUrl);
  }

  // 🔍 Récupérer un stagiaire par ID
  public getStagiaireParId(id: number): Observable<Stagiaire> {
    return this.http.get<Stagiaire>(`${this.apiUrl}/${id}`);
  }

  //les stagiaire d'un encarant
  public mesStagiaire(id: number): Observable<Stagiaire[]> {
    return this.http.get<Stagiaire[]>(`${this.apiUrl}/encadrant/${id}`);
  }

  // ✏️ Modifier un stagiaire
  public modifierStagiaire(id: number, stagiaire: Stagiaire): Observable<Stagiaire> {
    return this.http.put<Stagiaire>(`${this.apiUrl}/${id}`, stagiaire);
  }

  // 🗑️ Supprimer un stagiaire
  public supprimerStagiaire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
