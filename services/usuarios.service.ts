import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private httpClient:HttpClient) { }

  //Se hace post al endpoint para registrar usuario
  public registrarUsuario(usuario:any){
    return this.httpClient.post(`${baseUrl}/api/auth/registro`, usuario, {responseType: 'text'});
  }
}
