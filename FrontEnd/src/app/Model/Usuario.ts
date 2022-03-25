import { Carros } from "./Carros";
import { Venda } from "./Venda";
export class Usuario{
    public idUsuario: number
    public nome: string
    public email: string
    public senha: string
    public tipo: string
   public publicador: Carros[]
   public comprador: Venda[]
   
}