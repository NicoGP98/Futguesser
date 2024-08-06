import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { FormsModule, NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from '../services/login.service';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FooterComponent, FormsModule, NgClass],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  constructor(private router:Router, private snack:MatSnackBar, private loginService:LoginService) {  }

  loginData = {
    "nombreUsuario" : "",
    "password" : ""
  }

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

  loginSubmit(){
    //Se comprueba que el campo no esté vacío
    if(this.loginData.nombreUsuario.trim() == '' || this.loginData.nombreUsuario.trim() == null){
      this.snack.open("El nombre de usuario es requerido", "Aceptar", {
        duration:3000
      })
      return;
    }

    //Se comprueba que el campo no esté vacío
    if(this.loginData.password.trim() == '' || this.loginData.password.trim() == null){
      this.snack.open("La contraseña es requerida", "Aceptar", {
        duration:3000
      })
      return;
    }

    //Se genera el token de autenticación
    this.loginService.generateToken(this.loginData).subscribe(
      (data:any) =>{
        this.loginService.loginUser(data.token);
        this.loginService.getUsuarioActual().subscribe(
          (usuario:any) => {
            this.loginService.setUsuario(usuario);

          //Si todo es correcto el usuario se loguea y entra a Main
          if(this.loginService.getRolUsuario() == "ADMIN" || this.loginService.getRolUsuario() == "USER"){
            this.toMain();
            this.loginService.loginStatusSubject.next(true);
          } else{
            this.loginService.logout();
          }

        })
      }, (error) => {
        this.snack.open("Login incorrecto, vuelva a intentarlo", "Aceptar",{
          duration:3000
        })
      }
    )
  }

  toMain(){
    this.router.navigate([""]);
  }

}
