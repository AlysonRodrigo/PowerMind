import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrarComponent } from './cadastrar/cadastrar.component';
import { CarrosApagarComponent } from './carros-apagar/carros-apagar.component';
import { CarrosEditComponent } from './carros-edit/carros-edit.component';
import { EntrarComponent } from './entrar/entrar.component';
import { InicioComponent } from './inicio/inicio.component';

const routes: Routes = [
  { path: '', redirectTo: 'entrar', pathMatch: 'full' },
  { path: "entrar", component: EntrarComponent },
  { path: "cadastrar", component: CadastrarComponent },

  { path: "inicio", component: InicioComponent },

  { path: "carros-edit/:id", component: CarrosEditComponent },
  { path: "carros-apagar/:id", component: CarrosApagarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
