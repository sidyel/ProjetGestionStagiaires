import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {RhComponent} from "./rh/rh.component";
import {StagiaireComponent} from "./stagiaire/stagiaire.component";
import {EncadrantComponent} from "./encadrant/encadrant.component";
import {TacheComponent} from "./tache/tache.component";
import {ProjetComponent} from "./projet/projet.component";
import {AbsenceComponent} from "./absence/absence.component";
import {MessageComponent} from "./message/message.component";
import {AppComponent} from "./app.component";
import {LoginComponent} from "./login/login.component";
import {AdminComponent} from "./admin/admin.component";
import {AcceuilComponent} from "./acceuil/acceuil.component";
import {InterEncadrantComponent} from "./inter-encadrant/inter-encadrant.component";
import {MesStagiaireComponent} from "./mes-stagiaire/mes-stagiaire.component";
import {MesTacheComponent} from "./mes-tache/mes-tache.component";
import {MesProjetComponent} from "./mes-projet/mes-projet.component";
import {AssignStagiairesComponent} from "./projets/assign-stagiaires/assign-stagiaires.component";
import {InterStagiaireComponent} from "./inter-stagiaire/inter-stagiaire.component";
import {TacheStagiaireComponent} from "./tache-stagiaire/tache-stagiaire.component";
import {AbsenceStagiaireComponent} from "./absence-stagiaire/absence-stagiaire.component";
import {MesProjetsStagiaireComponent} from "./mes-projets-stagiaire/mes-projets-stagiaire.component";
import {StagiaireService} from "./services/stagiaire.service";
import {StagiaireDetailComponent} from "./stagiaire-detail/stagiaire-detail.component";
import {MessagerieStagiaireComponent} from "./messagerie-stagiaire/messagerie-stagiaire.component";
import {TableauDBStagiaireComponent} from "./tableau-dbstagiaire/tableau-dbstagiaire.component";
import {TableauDBEncadrantComponent} from "./tableau-dbencadrant/tableau-dbencadrant.component";
import {TableauDBrhComponent} from "./tableau-dbrh/tableau-dbrh.component";

const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: 'encadrant', component: EncadrantComponent },
      { path: 'stagiaires', component: StagiaireComponent },
      { path: 'rh', component: RhComponent },
      { path: 'tache', component: TacheComponent },
      { path: 'projet', component: ProjetComponent },
      { path: 'absence', component: AbsenceComponent },
      { path: 'tb', component: TableauDBrhComponent },
    ]
  },
  { path :"login", component : LoginComponent},
  { path: '', component: AcceuilComponent},
  {
    path: 'interEncadrant',
    component: InterEncadrantComponent,
    children: [
      { path: 'mesStagiaire', component: MesStagiaireComponent },
      { path: 'tb', component: TableauDBEncadrantComponent },
      { path: 'mesprojet', component: MesProjetComponent },
      { path: 'mestache', component: MesTacheComponent },
      { path: 'projet', component: ProjetComponent },
      { path: 'absence', component: AbsenceComponent },
      { path: 'message', component: MessageComponent },
    ]
  },

  {
    path: 'interStagiaire',
    component: InterStagiaireComponent,
    children: [
      { path: 'tacheStagiaire', component: TacheStagiaireComponent },
      { path: 'mesAbsence', component: AbsenceStagiaireComponent },
      { path: 'projetstagiaire', component: MesProjetsStagiaireComponent },
      { path: 'mesInfos', component: StagiaireDetailComponent },
      { path: 'mesMessage', component: MessagerieStagiaireComponent },
      { path: 'absence', component: AbsenceComponent },
      { path: 'tb', component: TableauDBStagiaireComponent },
    ]
  },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
