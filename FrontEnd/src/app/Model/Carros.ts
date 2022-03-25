import { Usuario } from "./Usuario"
import { Venda } from "./Venda"

export class Carros{
    public idCar: number
    public marca: String
    public modelo: String
    public foto: String
    public valor: number
    public criador:Usuario
    public valores:Venda
}