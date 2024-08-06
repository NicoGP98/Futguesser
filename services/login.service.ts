import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  //Generación del token
  public generateToken(loginData:any){
    return this.http.post(`${baseUrl}/api/auth/login`, loginData);
  }

  //Inicio de sesión y establecimiento del token en localStorage
  public loginUser(token:any){
    localStorage.setItem("token", token);
  }

  //Comprobación se si ya está logueado
  public isLogged(){
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      let tokenStr = localStorage.getItem("token");

      if(tokenStr == undefined || tokenStr == "" || tokenStr == null){
        return false;
      } else{
        return true;
      }
    } else {
      return false;
    }
  }

  //Cerrar sesión y eliminar token del localStorage
  public logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("usuario");
    return true;
  }

  //Obtener el token
  public getToken(){
    return localStorage.getItem("token");
  }

  public setUsuario(usuario:any){
    localStorage.setItem("usuario", JSON.stringify(usuario));
  }

  public getUsuario(){
    let usuarioStr = localStorage.getItem("usuario");
    if(usuarioStr != null){
      return JSON.parse(usuarioStr);
    } else{
      this.logout();
      return null;
    }
  }

  //Obtener el rol del usuario
  public getRolUsuario(): string {
    
    const usuario = this.getUsuario();
    return usuario.roles[0].nombreRol;
  }

  //Obtención del usuario que hay logueado actualmente
  public getUsuarioActual(){
    const headers = this.setTokenHeaders();
    return this.http.get(`${baseUrl}/api/auth/usuarioActual`, { headers });
  }

  public setTokenHeaders(){
    let token = this.getToken();
    //Configura las cabeceras
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return headers;
  }

}
