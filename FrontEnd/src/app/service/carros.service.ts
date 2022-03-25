import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Carros } from '../Model/Carros';
import { Venda } from '../Model/Venda';

@Injectable({
  providedIn: 'root'
})
export class CarrosService {

  constructor(private http: HttpClient) { }
  token ={
    headers:new HttpHeaders().set('Authorization',environment.token )
  }
  postCarro(carro:Carros):Observable<Carros>{
    return this.http.post<Carros>('http://localhost:8080/carros/salvar',carro)
  }
  getAllCarro():Observable<Carros[]>{
    return this.http.get<Carros[]>('http://localhost:8080/carros/todos')
  }
  getByMarca(marca: string):Observable<Carros[]>{
    return this.http.get<Carros[]>(`http://localhost:8080/carros/marca/${marca}`)
  }
  deleteCarro(idCar: number){
    return this.http.delete(`http://localhost:8080/carros/${idCar}`)
  }
  PutCar(car: Carros): Observable<Carros>{
    return this.http.put<Carros>('http://localhost:8080/carros/atualizar', car)
  }
  getByIdCarros(id: number):Observable<Carros>{
    return this.http.get<Carros>(`http://localhost:8080/carros/${id}`)
  }
postByVenda(venda:Venda):Observable<Venda>{
  return this.http.post<Venda>('http://localhost:8080/venda/calcular',venda)
}
}
