import { Injectable } from '@angular/core';
import { HttpClient, HttpClientJsonpModule } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { UsuarioLogin } from '../Model/UsuarioLogin';
import { Usuario } from '../Model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }
  entrar(userLogin: UsuarioLogin): Observable<UsuarioLogin> {
    return this.http.put<UsuarioLogin>('http://localhost:8080/usuario/credenciais', userLogin)
  }
  cadastrar(user: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>('http://localhost:8080/usuario/salvar', user)
  }
   logado() {
     let ok: boolean = false

     if (environment.token != '') {
       ok = true
     }

     return ok
  }
  typeUser(){
    let ok: boolean = false

    if(environment.tipo == "adm"){
      ok = true
    }

    return ok
  }
}