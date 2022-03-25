import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { UsuarioLogin } from '../Model/UsuarioLogin';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-entrar',
  templateUrl: './entrar.component.html',
  styleUrls: ['./entrar.component.css']
})
export class EntrarComponent implements OnInit {

  userLogin: UsuarioLogin = new UsuarioLogin()
  constructor(
    private auth: AuthService,
    private router: Router,
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
  }
  entrar() {
    this.auth.entrar(this.userLogin).subscribe((resp: UsuarioLogin) => {
      this.userLogin = resp

      environment.token = this.userLogin.token
      environment.nome = this.userLogin.nome
      environment.tipo = this.userLogin.tipo

      this.router.navigate(['/inicio'])
    }, erro => {
      if (erro.status == 400) {
        alert('Email ou senha Incorreta!')
      }
    })
  }
}
