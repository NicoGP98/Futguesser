import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class EstadisticasService {

  constructor(private http:HttpClient, private loginService:LoginService) { }

  //Se hace el post al endpoint del back para insertar estadística
  public insertarEstadistica(acertado: boolean, numIntentos: number) {
    const headers = this.loginService.setTokenHeaders();
    const estadistica = { acertado, numIntentos };

    return this.http.post(`${baseUrl}/estadisticas/insertaStat`, estadistica, { headers, responseType: 'json' });
  }

  //Se hace el get al endpoint de obtener estadísticas
  public obtenerEstadisticasUsuario() {
    const headers = this.loginService.setTokenHeaders();
    return this.http.get(`${baseUrl}/estadisticas/statsUsuario`, { headers, responseType: 'json' });
  }
}
