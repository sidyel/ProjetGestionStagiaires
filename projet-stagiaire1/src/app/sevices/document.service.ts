import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
export interface DocumentMeta {
  iddoc: number;
  type: string;
  date: string;
  idstagiaire: number;
  nomFichier: string;
  url: string;
}

@Injectable({ providedIn: 'root' })
export class DocumentService {
  private baseUrl = 'http://localhost:8081/documents';

  constructor(private http: HttpClient) {}

  /** Génère l’attestation et renvoie la méta‑info */
  generateAttestation(idStagiaire: number): Observable<DocumentMeta> {
    return this.http.post<DocumentMeta>(`${this.baseUrl}/attestation/${idStagiaire}`, {});
  }

  /** Télécharge le PDF en binaire */
  downloadPdf(idStagiaire: number): Observable<Blob> {
    return this.http.get(
      `${this.baseUrl}/attestation/${idStagiaire}/pdf`,
      { responseType: 'blob' }
    );
  }
}
