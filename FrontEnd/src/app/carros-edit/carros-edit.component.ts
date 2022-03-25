import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Carros } from '../Model/Carros';
import { AlertasService } from '../service/alertas.service';
import { CarrosService } from '../service/carros.service';

@Component({
  selector: 'app-carros-edit',
  templateUrl: './carros-edit.component.html',
  styleUrls: ['./carros-edit.component.css']
})
export class CarrosEditComponent implements OnInit {

  postagem: Carros = new Carros()
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private postagemService: CarrosService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)
    if (environment.token == '') {
      this.alertas.showAlertDanger('Sua seção espirou, faça login novamente...')
      this.router.navigate(['/entrar'])
    }
    let id = this.route.snapshot.params['id']
    this.findByIdPostagem(id)
  }
  findByIdPostagem(id: number) {
    this.postagemService.getByIdCarros(id).subscribe((resp: Carros) => {
      this.postagem = resp
    })
  }

  atualizar() {
    this.postagemService.PutCar(this.postagem).subscribe((resp: Carros) => {
      this.postagem = resp
      this.alertas.showAlertInfo('Postagem atualizada com Sucesso')
      this.router.navigate(['/inicio'])
    })
  }
}
