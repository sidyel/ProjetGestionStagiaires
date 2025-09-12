export interface Tache {
  idtache?: number;
  titre: string;
  description: string;
  dateDebut: string;
  dateFin: string;
  statut: 'EN_ATTENTE' | 'EN_COURS' | 'TERMINEE';  // <-- ajouté TERMINEE
  idstagiaire: number;
  idencadrant: number;
  idprojet: number;
  stagiaire?: any;
  projet?: any;
  encadrant?: any;
}
