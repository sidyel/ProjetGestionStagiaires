import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin!:FormGroup;
  constructor(private fb : FormBuilder,private autService:AuthService,
              private router:Router) { }

  ngOnInit(): void {
    this.formLogin=this.fb.group({
      username:this.fb.control(""),
      password:this.fb.control("")
    })
  }

  handlogin(){
    let username=this.formLogin.value.username
    let password=this.formLogin.value.password
    this.autService.login(username, password).subscribe({
      next: value => {
        this.autService.profil(value); // stockage du token
        const role = this.autService.getRole();
        if (this.autService.idpersonne != null) {
          console.log('ID valide:', this.autService.idpersonne);
        } else {
          console.warn('Aucun ID détecté !');
        }


        if (role === 'RH') this.router.navigate(['/admin/tb']);
        else if (role === 'STAGIAIRE') this.router.navigate(['/interStagiaire/tb']);
        else if (role === 'ENCADRANT') this.router.navigate(['/interEncadrant/tb']);
        else this.router.navigate(['/login']); // par défaut
      },
      error: err => {
        console.error(err);
      }
    });

    console.log(this.formLogin.value)
  }

}
