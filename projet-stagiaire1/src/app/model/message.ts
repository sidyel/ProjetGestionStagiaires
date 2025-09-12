import {Encadrant} from "./encadrant";
import {Stagiaire} from "./Stagiaire";

export interface Message {
  id?: number;
  idStagiaire: number;
  idEncarant: number;  // À corriger si c'est une faute de frappe (voir remarque ci-dessous)
  contenu: string;
  dateEnvoi: string; // Format 'YYYY-MM-DD'
  encadrant: Encadrant,
  stagiaire: Stagiaire
}
