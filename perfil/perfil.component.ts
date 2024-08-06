// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { LoginService } from '../services/login.service';
// import { EstadisticasService } from '../services/estadisticas.service';
// import { CommonModule, NgClass } from '@angular/common';
// import { FooterComponent } from '../footer/footer.component';

// @Component({
//   selector: 'app-perfil',
//   standalone: true,
//   imports: [CommonModule, NgClass, FooterComponent],
//   templateUrl: './perfil.component.html',
//   styleUrl: './perfil.component.css'
// })
// export class PerfilComponent implements OnInit{

//   estadisticas: any;

// constructor(private router:Router, public loginService:LoginService, private estadisticasService:EstadisticasService){}

// isDarkMode: boolean = false;

// ngOnInit(): void {
//   this.isDarkMode = localStorage.getItem('darkMode') === 'true';
//   this.cambiarModo();

//   //Se llama al método para obtener las stats del usuario y mostrarlas
//   this.estadisticasService.obtenerEstadisticasUsuario().subscribe({
//     next: data => {
//       this.estadisticas = data;
//     },
//     error: err => {
//       console.log('Error al obtener las estadísticas', err);
//     }
//   });
// }

// cambiarModo() {
//   const body = document.body;
//   if (this.isDarkMode) {
//     body.classList.add('dark-mode');
//   } else {
//     body.classList.remove('dark-mode');
//   }
// }

// public logout(){
//   this.loginService.logout();
//   this.toMain();
// }

// toMain(){
//   this.router.navigate([""]);
// }

// }

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { EstadisticasService } from '../services/estadisticas.service';
import { CommonModule, NgClass } from '@angular/common';
import { FooterComponent } from '../footer/footer.component';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective  } from 'ng2-charts';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, NgClass, FooterComponent, BaseChartDirective ],
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  estadisticas: any;

  //Se definen los datos que tendrá el gráfico circular.
  aciertosFallosData: ChartData<'pie', number[], string | string[]> = {
    labels: ['Aciertos', 'Fallos'],
    datasets: [
      {
        data: [0, 0]
      }
    ]
  };

  //Se definen los datos que tendrá el gráfico de barras.
  intentosData: ChartData<'bar', number[], string | string[]> = {
    labels: ['Primer Intento', 'Segundo Intento', 'Tercer Intento', 'Cuarto Intento', 'Quinto Intento', 'Sexto Intento'],
    datasets: [
      {
        //Se establecen en 0 los aciertos por defecto para modificarlos con actualizarGrafica()
        data: [0, 0, 0, 0, 0, 0],
        label: 'Aciertos por intento'
      }
    ]
  };

  constructor(private router: Router, public loginService: LoginService, private estadisticasService: EstadisticasService) { }

  isDarkMode: boolean = false;

  ngOnInit(): void {
    this.isDarkMode = localStorage.getItem('darkMode') === 'true';
    this.cambiarModo();

    // Se llama al método para obtener las stats del usuario y mostrarlas
    this.estadisticasService.obtenerEstadisticasUsuario().subscribe({
      next: data => {
        this.estadisticas = data;
        this.actualizarGrafica();
      },
      error: err => {
        console.log('Error al obtener las estadísticas', err);
      }
    });
  }

  cambiarModo() {
    const body = document.body;
    if (this.isDarkMode) {
      body.classList.add('dark-mode');
    } else {
      body.classList.remove('dark-mode');
    }
  }

  //Actualiza los datos de cada gráfico.
  actualizarGrafica() {
    this.aciertosFallosData.datasets[0].data = [this.estadisticas.aciertos, this.estadisticas.fallos];
    this.intentosData.datasets[0].data = this.estadisticas.aciertosPorIntento;
  }

  public logout() {
    this.loginService.logout();
    this.toMain();
  }

  toMain() {
    this.router.navigate([""]);
  }
}