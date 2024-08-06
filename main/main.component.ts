import { Component, NgModule, OnInit } from '@angular/core';
import { BackendService } from '../backend.service';
import { Pistas } from '../Pistas';
import { FormsModule, FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { CommonModule } from '@angular/common';
import { EstadisticasService } from '../services/estadisticas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, MatAutocompleteModule, MatInputModule, MatFormFieldModule, CommonModule],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  //Variables
  pistas: Pistas = {nombreJugador:"", stats:"", trayectoria:"", anio:"", escudo:"",  nombreEquipo:"", ucls:"", mundiales:"", nacionalidad:""};
  contador: number = 0;
  nombre = "Nico";
  stats: String = "Stats";
  trayectoria: String = "Trayectoria";
  ucls: String = "UCLs y Mundiales";
  mundiales: String = "";
  uclImageHtml: string = 'UCLs y Mundiales';
  mundialesImageHtml: string = '';

  equipoAnio: String = "";
  imgEscudo: String = "../../assets/Imagenes/Logos/ball.png";
  imgBandera: String = "../../assets/Imagenes/Logos/bandera.png";
  listaNombres: string[] = [];

  myControl = new FormControl('');
  filteredOptions: Observable<string[]>;

  terminado: Boolean = false;
  acertado: boolean = false;
  numIntentos: Number = 1;

  textoBoton: string = "Adivinar";
  mensajeCompartir: string = "";

  constructor(private service: BackendService, private estadisticasService:EstadisticasService){
    //Para el autocompletado del input
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value ?? ''))
    );
  }

  //Método para filtrar un alista de nombre por el value insertado para el autocompletado
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.listaNombres.filter(nombre => nombre.toLowerCase().includes(filterValue));
  }

  ngOnInit(): void {

    //Al iniciarse, se escoge un jugador aleatorio
    //Y se obtienen las pistas referente al jugador escogido
    this.service.escogerJugadorAleatorio().subscribe({
      next: idMaximo => {
        this.service.getPistas(this.generarNumeroAleatorio(idMaximo)).subscribe({
          next: data => {
            this.pistas.nombreJugador = data.nombreJugador;
            this.pistas.stats = data.stats;
            this.pistas.trayectoria = data.trayectoria;
            this.pistas.anio = data.anio;
            this.pistas.escudo = data.escudo;
            this.pistas.nombreEquipo = data.nombreEquipo;
            this.pistas.ucls = data.ucls;
            this.pistas.mundiales = data.mundiales;
            this.pistas.nacionalidad = data.nacionalidad;
          },
          error: err => {console.log(err)}
        });
      },
      error: er => {console.log(er);}
    });

    this.traerJugadores();

  }

  traerJugadores(){
    this.service.getNombres().subscribe({
      next:data => {
        this.listaNombres = data;
      },
      error: err => {console.log(err)}
    });
  }

  generarNumeroAleatorio(max: number): number {
    return Math.floor(Math.random() * (max - 1 + 1)) + 1;
  }

  //Se comprueba si se acierta el jugador
  //Si no, entra en juego el switch para ir mostrando pistas
  adivinar(){
    if(this.myControl.value != this.pistas.nombreJugador){
      this.siguientePista();
    } else {
      switch (this.numIntentos) {
        case 1:
          Swal.fire({
            title: "¡Acertaste!",
            text: "¡A la primera, no te lo crees ni tú!",
            icon: "success"
          });
          break;
        case 2:
          Swal.fire({
            title: "¡Acertaste!",
            text: "¡A la segunda, menudo crack!",
            icon: "success"
          });
          break;
        case 3:
          Swal.fire({
            title: "¡Acertaste!",
            text: "¡A la tercera, no está nada mal!",
            icon: "success"
          });
          break;
        case 4:
          Swal.fire({
            title: "¡Acertaste!",
            text: "A la cuarta, está bien pero no te vengas arriba eh",
            icon: "success"
          });
          break;
        case 5:
          Swal.fire({
            title: "¡Acertaste!",
            text: "A la quinta. Se puede hacer mejor, al menos has acertado",
            icon: "success"
          });
          break;
        case 6:
          Swal.fire({
            title: "¡Acertaste!",
            text: "A la sexta, ya te vale...",
            icon: "success"
          });
          break;
      }      
      
      this.terminado = true;
      this.acertado = true;

      this.textoBoton = "Compartir";
      this.mensajeCompartir = `He adivinado el jugador en ${this.numIntentos} intentos en Futguesser`;
    }

    if(this.terminado){
      /*Se inserta la stat de la partida*/
      this.insertarEstadistica();
    }
    
  }

  //Método para saber qué pista mostrar en base al contador creado de forma global
  siguientePista(){
    this.contador++;
    switch(this.contador){
      case 1:
        this.stats = this.pistas.stats;
        this.numIntentos = 2;
        break;
      case 2:
        this.trayectoria = this.pistas.trayectoria;
        this.numIntentos = 3;
        break;
      case 3:
        this.equipoAnio = (this.pistas.nombreEquipo + " " + this.pistas.anio);
        this.imgEscudo = ("../../assets/Imagenes/Escudos/" + this.pistas.escudo + ".png");
        this.numIntentos = 4;
        break;
      case 4:
        this.uclImageHtml = `${this.pistas.ucls}x<img src="../../assets/Imagenes/Logos/ucl.png" width="80" height="70">`;
        this.mundialesImageHtml = `${this.pistas.mundiales}x<img src="../../assets/Imagenes/Logos/wc1.png" width="80" height="70">`;
        this.numIntentos = 5;
        break;
      case 5:
        this.imgBandera = ("../../assets/Imagenes/Banderas/" + this.pistas.nacionalidad + ".png");
        this.numIntentos = 6;
        break;
      case 6:
          Swal.fire({
            title: "Has fallado...",
            text: "¿¿Qué quieres, más pistas?? El jugador era: " + this.pistas.nombreJugador,
            icon: "error"
          });
        this.terminado = true;
        this.numIntentos = 7;

        this.textoBoton = "Compartir";
        this.mensajeCompartir = "He fallado al intentar adivinar el jugador en Futguesser...";
        break;
      }
  }

  //Método para insertar la estadística
  insertarEstadistica() {
    this.estadisticasService.insertarEstadistica(this.acertado, this.numIntentos as number).subscribe({
      next: data => {
        console.log('Estadística registrada con éxito', data);
      },
      error: err => {
        console.log('Error al registrar la estadística', err);
      }
    });
  }

  //Se convierte el botón de adivinar en compartir una vez se acaba la partida
  //Posibilidad de copiar al portapapeles
  compartir() {
    const text = this.acertado ? `He adivinado el jugador en ${this.numIntentos} intentos en Futguesser` : `No he logrado adivinar el jugador en Futguesser...`;
    navigator.clipboard.writeText(text).then(() => {
      Swal.fire({
        title: '¡Listo para compartir!',
        text: '¡Texto copiado al portapapeles!',
        icon: 'success'
      });
    }).catch(err => {
      console.error('Error al copiar al portapapeles: ', err);
      Swal.fire({
        title: 'Error',
        text: 'No se pudo copiar el texto',
        icon: 'error'
      });
    });
  }

}
