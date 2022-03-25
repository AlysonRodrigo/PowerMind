import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment.prod';
import { Carros } from '../Model/Carros';
import { AlertasService } from '../service/alertas.service';
import { CarrosService } from '../service/carros.service';

@Component({
  selector: 'app-carros-apagar',
  templateUrl: './carros-apagar.component.html',
  styleUrls: ['./carros-apagar.component.css']
})
export class CarrosApagarComponent implements OnInit {

  postagem: Carros = new Carros()
  idCar: number
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private carrosService: CarrosService,
    private alertas: AlertasService
  ) { }

  ngOnInit() {
    window.scroll(0, 0)

    if (environment.token == '') {
      this.alertas.showAlertDanger('Sua seção espirou, faça login novamente...')
      this.router.navigate(['/entrar'])
    }
    this.idCar = this.route.snapshot.params['id']
    this.findByIdCarros(this.idCar)
  }
  findByIdCarros(id: number) {
    this.carrosService.getByIdCarros(id).subscribe((resp: Carros) => {
      this.postagem = resp
    })
  }

  apagar() {
    this.carrosService.deleteCarro(this.idCar).subscribe(() => {
      this.alertas.showAlertSucess('Postagem apagada com Sucesso!!')
      this.router.navigate(['/inicio'])
    })
  }
}


