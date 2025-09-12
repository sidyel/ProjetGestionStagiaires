import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {ChefRh} from "../model/ChefRh";

@Injectable({
  providedIn: 'root'
})
export class RhService {

  private apiUrl = 'http://localhost:8080/chefrhs';

  constructor(private http: HttpClient) {}

  // Créer un RH
  public ajouterRh(chefRh: ChefRh): Observable<ChefRh> {
    return this.http.post<ChefRh>(this.apiUrl, chefRh);
  }

  genererAttestation(idStagiaire: number): Observable<Document> {
    return this.http.post<Document>(`http://localhost:8081/attestation/${idStagiaire}`, {});
  }

  //  Obtenir tous les RH
  public getAllRh(): Observable<ChefRh[]> {
    return this.http.get<ChefRh[]>(this.apiUrl);
  }

  //  Obtenir un RH par ID
  public getRhById(id: number): Observable<ChefRh> {
    return this.http.get<ChefRh>(`${this.apiUrl}/${id}`);
  }

  //  Modifier un RH
  public modifierRh(id: number, rh: ChefRh): Observable<ChefRh> {
    return this.http.put<ChefRh>(`${this.apiUrl}/${id}`, rh);
  }

  //  Supprimer un RH
  public supprimerRh(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
