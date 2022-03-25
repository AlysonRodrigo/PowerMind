import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Carros } from '../Model/Carros';
import { Usuario } from '../Model/Usuario';
import { Venda } from '../Model/Venda';
import { AlertasService } from '../service/alertas.service';
import { AuthService } from '../service/auth.service';
import { CarrosService } from '../service/carros.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {
  postagem: Carros = new Carros()
  vendas :Venda =new Venda()
  listaPostagem: Carros[]
  usuar: Usuario = new Usuario()
  idUsuarios = environment.id
  key = "valor"
  reverse = false
  constructor(
    private router: Router,
    public authService: AuthService,
    private alertas: AlertasService,
    public carro: CarrosService

  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (environment.token == '') {
      this.alertas.showAlertDanger('Sua seção espirou, faça login novamente...')
      this.router.navigate(['/entrar'])

    }
    this.getAllCarro()
  }
  getAllCarro() {
    this.carro.getAllCarro().subscribe((resp: Carros[]) => {
      this.listaPostagem = resp
    })
  }
  postar() {
    this.usuar.idUsuario = this.idUsuarios
    this.postagem.criador = this.usuar
    this.carro.postCarro(this.postagem).subscribe((resp: Carros) => {
      console.log(resp);
      
      this.postagem = resp
      this.alertas.showAlertSucess('Postagem realizada com sucesso!!')
      this.postagem = new Carros()
      this.getAllCarro()
    })
  }

  calcularValor(){
     this.carro.postByVenda(this.vendas).subscribe((resp: Venda) => {
       this.vendas = resp
     })
  }

  userAdm() {
    if (this.usuar.tipo == "adm") {
      return true;
    } else {
      return false;
    }
  }
}
