import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../Model/Usuario';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';

import { error } from '@angular/compiler/src/util';
import { HttpResponse } from '@angular/common/http';
@Component({
  selector: 'app-cadastrar',
  templateUrl: './cadastrar.component.html',
  styleUrls: ['./cadastrar.component.css']
})
export class CadastrarComponent implements OnInit {
  user: Usuario = new Usuario
  confirmSenha: string
  confirmPassword1: string
  code: string
  usuar: string

  constructor(
    private authService: AuthService,
    private router: Router,
    private alertas: AlertasService
  ) { }

  ngOnInit(): void {
    window.scroll(0, 0)
  }
  confirmeSenha(event: any) {
    this.confirmSenha = event.target.value

  }
  tipouser(event: any) {
    this.usuar = event.target.value
  }

  userAdm() {
    if (this.user.tipo == "Adm") {
      return true;
    } else {
      return false;
    }
  }

  register() {
    if (this.user.tipo == "Administrador" && this.code == "aaa") {
      this.authService.cadastrar(this.user).subscribe((resp: Usuario) => {
        this.user = resp
        this.router.navigate(['/inicio'])
        alert("Usuário cadastrado com sucesso!")
      }, error => {
        if (error.status == 400) {
          alert("Este Email ja existe! Por favor utilize um email diferente.")
        }
      })
    } else if (this.user.tipo == "Normal") {
      this.authService.cadastrar(this.user).subscribe((resp: Usuario) => {
        this.user = resp
        this.router.navigate(["/inicio"])
        alert("Usuário cadastrado com sucesso!")
      }, error => {
        if (error.status == 400) {
          alert("Este Email ja existe! Por favor utilize um email diferente.")
        }
      })
    } else {
      alert("Dados incorretos, por favor corrigir.")
      this.router.navigate(['/cadastrar'])
    }
  }
}
