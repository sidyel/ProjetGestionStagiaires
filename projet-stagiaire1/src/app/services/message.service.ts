import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Message } from '../model/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private apiUrl = 'http://localhost:8083/messages';

  constructor(private http: HttpClient) {}

  // ➕ Envoyer un message
  public envoyerMessage(message: Message): Observable<Message> {
    return this.http.post<Message>(this.apiUrl, message);
  }

  // 📑 Récupérer tous les messages
  public getTousLesMessages(): Observable<Message[]> {
    return this.http.get<Message[]>(this.apiUrl);
  }

  // 🔍 Récupérer un message par ID
  public getMessageParId(id: number): Observable<Message> {
    return this.http.get<Message>(`${this.apiUrl}/${id}`);
  }

  // 📥 Récupérer les messages d’un stagiaire
  public getMessagesParStagiaire(idStagiaire: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.apiUrl}/stagiaire/${idStagiaire}`);
  }

  // 📤 Récupérer les messages reçus par un encadrant
  public getMessagesParEncadrant(idEncadrant: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.apiUrl}/encadrant/${idEncadrant}`);
  }

  // 🔄 Récupérer les messages entre un stagiaire et un encadrant
  public getMessagesEntre(idStagiaire: number, idEncadrant: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.apiUrl}/stagiaire-encadrant/${idStagiaire}/${idEncadrant}`);
  }

  // 🗑️ Supprimer un message
  public supprimerMessage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
