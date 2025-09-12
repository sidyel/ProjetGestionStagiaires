import { Stagiaire } from './Stagiaire';

export interface Absence {
  idabsence?: number;
  dateDebut: string;       // 'YYYY-MM-DD'
  dateFin: string;         // 'YYYY-MM-DD'
  motif: string;
  justification: string;
  statut?: 'EN_ATTENTE' | 'VALIDE' | 'REFUSE'; // selon ton backend
  idstagiaire: number;
  stagiaire?: Stagiaire;       // si les détails du stagiaire sont inclus dans la réponse
}
