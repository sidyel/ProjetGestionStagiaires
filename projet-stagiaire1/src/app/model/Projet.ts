export interface Projet {
  idprojet?: number;
  titre: string;
  description: string;
  dateDebut: string;    // Format : 'YYYY-MM-DD'
  dateFin: string;
  idencadrant: number;
  encadrant?: any;// Ou un type spécifique si tu as une interface Encadrant

  avancement?: number; // 0–100 en %
}
