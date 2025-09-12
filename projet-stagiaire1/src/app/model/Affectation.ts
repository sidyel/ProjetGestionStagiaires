// src/app/services/projet‑stagiaire.service.ts
export interface Affectation {
  /** Identifiant de la ligne d’affectation */
  id: number;
  /** Référence à l’étudiant */
  idstagiaire: number;
  /** Référence au projet */
  idprojet?: number;
}
