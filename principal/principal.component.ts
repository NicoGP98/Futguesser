import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { MainComponent } from '../main/main.component';
import { FooterComponent } from '../footer/footer.component';
import { LoginComponent } from '../login/login.component';
import { RegistroComponent } from '../registro/registro.component';
import { InstruccionesComponent } from '../instrucciones/instrucciones.component';
import { AjustesComponent } from '../ajustes/ajustes.component';
import { AppComponent } from '../app.component';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-principal',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, MainComponent, FooterComponent,
    LoginComponent, RegistroComponent, InstruccionesComponent,
    AjustesComponent, AppComponent, NgClass],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css'
})
export class PrincipalComponent {
  title = 'futguesser';

  isDarkMode: boolean = false;

  ngOnInit(): void {
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      this.isDarkMode = localStorage.getItem('darkMode') === 'true';
      this.cambiarModo();
    }
  }

  cambiarModo() {
    const body = document.body;
    if (this.isDarkMode) {
      body.classList.add('dark-mode');
    } else {
      body.classList.remove('dark-mode');
    }
  }
}
