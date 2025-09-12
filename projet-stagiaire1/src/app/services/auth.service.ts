

export interface TokenPayload {
  sub: string;
  scope: string;
  idpersonne: number;
  nom: string;
  prenom: string;
  role: string;
  exp: number;
  iat: number;
}


import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { isPlatformBrowser } from '@angular/common';
import { jwtDecode, JwtPayload } from "jwt-decode";

interface MonTokenPayload extends JwtPayload {
  scope: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  connecter: boolean = false;
  roles: string = '';
  username: string = '';
  accessToken!: string;

  // ✅ Propriétés à ajouter
  idpersonne!: number;
  nom: string = '';
  prenom: string = '';
  role: string = '';

  constructor(private http: HttpClient, @Inject(PLATFORM_ID) private platformId: Object) {}

  public login(username: string, password: string) {
    let param = new HttpParams()
      .set("username", username)
      .set("password", password);

    let option = {
      headers: new HttpHeaders().set("Content-Type", "application/x-www-form-urlencoded")
    };

    return this.http.post("http://localhost:8080/login", param, option);
  }

  profil(value: any) {
    this.connecter = true;
    this.accessToken = value['access-token'];

    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('access-token', this.accessToken);
    }

    const decoded = jwtDecode<TokenPayload>(this.accessToken);
    this.username = decoded.sub;
    this.roles = decoded.scope;

    // ✅ Remplissage des infos personnelles
    this.idpersonne = decoded.idpersonne;
    this.nom = decoded.nom;
    this.prenom = decoded.prenom;
    this.role = decoded.role;
  }

  initialiserProfilDepuisStorage() {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('access-token');
      if (token) {
        this.connecter = true;
        this.accessToken = token;
        const decoded = jwtDecode<TokenPayload>(token);
        this.username = decoded.sub ?? '';
        this.roles = decoded.scope;
        this.idpersonne = decoded.idpersonne;
        this.nom = decoded.nom;
        this.prenom = decoded.prenom;
        this.role = decoded.role;
        console.log(this.idpersonne)
      }
    }
  }

  getRole(): string {
    return this.roles;
  }

  logout() {
    this.connecter = false;
    this.accessToken = '';
    this.username = '';
    this.roles = '';
    this.idpersonne = 0;
    this.nom = '';
    this.prenom = '';
    this.role = '';

    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('access-token');
    }
  }
}
