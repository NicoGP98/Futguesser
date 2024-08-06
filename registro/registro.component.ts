import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { UsuariosService } from '../services/usuarios.service';
import { FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { NgClass } from '@angular/common';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FooterComponent, FormsModule, NgClass],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {

  public usuario = {
    nombreUsuario : '',
    email : '',
    password : ''
  }


  constructor(
    private router:Router,
    private usuarioService:UsuariosService, 
    private snack:MatSnackBar,
  ) {  }

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

  toMain(){
    this.router.navigate([""]);
  }

  submit(){
    //Se comprueba que se haya insertado nombre de usuario
    if(this.usuario.nombreUsuario == '' || this.usuario.nombreUsuario == null){
       this.snack.open("Es obligatorio insertar un nombre de usuario!!", "Aceptar", {
         duration : 3000,
         verticalPosition : "top",
        horizontalPosition : "center"
      });
      return;
    }

    //Para validar que el nombre tiene más de 3 caracteres
    if (this.usuario.nombreUsuario.length < 3) {
      this.snack.open("El nombre de usuario debe tener al menos 3 caracteres", "Aceptar", {
        duration: 3000,
        verticalPosition: "top",
        horizontalPosition: "center"
      });
      return;
    }

    //Se valida el email usando un pattern
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]{2,3}$/;
    if (!emailPattern.test(this.usuario.email)) {
      this.snack.open("Por favor, ingrese un email válido", "Aceptar", {
        duration: 3000,
        verticalPosition: "top",
        horizontalPosition: "center"
      });
      return;
    }

    //Para validar que la contraseña tiene más de 3 caracteres
    if (this.usuario.password.length < 3) {
      this.snack.open("La contraseña debe tener al menos 3 caracteres", "Aceptar", {
        duration: 3000,
        verticalPosition: "top",
        horizontalPosition: "center"
      });
      return;
    }

    this.usuarioService.registrarUsuario(this.usuario).subscribe(
      (data) => {
          console.log(data);
          Swal.fire("Usuario registrado", "Usuario registrado con éxito", "success");
        
      }, (error) => {
        console.log(error);
        this.snack.open(error.error , "Aceptar", {
          duration : 3000,
          verticalPosition : "top",
          horizontalPosition : "right"
        });
      }
    )
  }
}
