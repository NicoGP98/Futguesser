import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { CommonModule, NgClass } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, NgClass],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router:Router, public loginService:LoginService) {  }

  isDarkMode: boolean = false;

  ngOnInit(): void {
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      this.isDarkMode = localStorage.getItem('darkMode') === 'true';
      this.cambiarModo();
    }
  }

  toLogin(){
    this.router.navigate(["/login"]);
  }

  toPerfil(){
    this.router.navigate(["/perfil"]);
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