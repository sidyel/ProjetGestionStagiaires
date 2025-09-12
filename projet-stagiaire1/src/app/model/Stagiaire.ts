import { Encadrant } from "./encadrant";

export interface Stagiaire {
  idpersonne?: number;
  nom: string;
  prenom: string;
  datenaiss: string;
  lieunaiss: string;
  login: string;
  motpasse: string;
  telephonne: string;
  email: string;
  matricule: string;
  cin: string;
  etats: string;
  niveauEtude: string;
  specialite: string;
  etablissementOrigine: string;
  dateDebut: string;
  dateFin: string;
  statut: string;
  domaineStage: string;
  encadrant: Encadrant; // 👈 Encadrant complet (avec nom, prénom…)
  idencadrant:number
}
