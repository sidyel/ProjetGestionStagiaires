// src/app/students‑projects/student‑with‑projects.model.ts
import { Stagiaire } from '../model/Stagiaire';
import { Projet }    from '../model/Projet';
import {Affectation} from "./Affectation";

export interface StudentWithProjects {
  /** L’étudiant lui‑même */
  stagiaire: Stagiaire;

  /** La liste des projets auxquels il est affecté */
  projets: Projet[];

  /** La liaison projet–étudiant reçue du back (ex : { id, idstagiaire, idprojet }) */
  affectations: Affectation[];
}
