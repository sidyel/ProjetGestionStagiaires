import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TacheComponent } from './tache/tache.component';
import { StagiaireComponent } from './stagiaire/stagiaire.component';
import { EncadrantComponent } from './encadrant/encadrant.component';
import { RhComponent } from './rh/rh.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { ProjetComponent } from './projet/projet.component';
import { AbsenceComponent } from './absence/absence.component';
import { MessageComponent } from './message/message.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AcceuilComponent } from './acceuil/acceuil.component';
import {AppHttpInterceptor} from "./interceptor/app-http.interceptor";
import { InterEncadrantComponent } from './inter-encadrant/inter-encadrant.component';
import { InterStagiaireComponent } from './inter-stagiaire/inter-stagiaire.component';
import { MesStagiaireComponent } from './mes-stagiaire/mes-stagiaire.component';
import { MesTacheComponent } from './mes-tache/mes-tache.component';
import { MesProjetComponent } from './mes-projet/mes-projet.component';
import { AssignStagiairesComponent } from './projets/assign-stagiaires/assign-stagiaires.component';
import { TacheStagiaireComponent } from './tache-stagiaire/tache-stagiaire.component';
import { AbsenceStagiaireComponent } from './absence-stagiaire/absence-stagiaire.component';
import { MesProjetsStagiaireComponent } from './mes-projets-stagiaire/mes-projets-stagiaire.component';
import { StagiaireDetailComponent } from './stagiaire-detail/stagiaire-detail.component';
import { MessagerieStagiaireComponent } from './messagerie-stagiaire/messagerie-stagiaire.component';
import { TableauDBStagiaireComponent } from './tableau-dbstagiaire/tableau-dbstagiaire.component';
import { TableauDBEncadrantComponent } from './tableau-dbencadrant/tableau-dbencadrant.component';
import { TableauDBrhComponent } from './tableau-dbrh/tableau-dbrh.component';

@NgModule({
  declarations: [
    AppComponent,
    TacheComponent,
    StagiaireComponent,
    EncadrantComponent,
    RhComponent,
    ProjetComponent,
    AbsenceComponent,
    MessageComponent,
    LoginComponent,
    AdminComponent,
    AcceuilComponent,
    InterEncadrantComponent,
    InterStagiaireComponent,
    MesStagiaireComponent,
    MesTacheComponent,
    MesProjetComponent,
    AssignStagiairesComponent,
    TacheStagiaireComponent,
    AbsenceStagiaireComponent,
    MesProjetsStagiaireComponent,
    StagiaireDetailComponent,
    MessagerieStagiaireComponent,
    TableauDBStagiaireComponent,
    TableauDBEncadrantComponent,
    TableauDBrhComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
