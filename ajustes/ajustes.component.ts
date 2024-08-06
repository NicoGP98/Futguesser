import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-ajustes',
  standalone: true,
  imports: [FooterComponent, HeaderComponent],
  templateUrl: './ajustes.component.html',
  styleUrl: './ajustes.component.css'
})
export class AjustesComponent {

  isDarkMode: boolean = false;

  constructor(private router:Router) {  }

  ngOnInit(): void {
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      this.isDarkMode = localStorage.getItem('darkMode') === 'true';
      this.cambiarModo();
    }
  }

  toMain() {
    this.router.navigate([""]);
  }

  setTheme(isDark: boolean) {
    this.isDarkMode = isDark;
    localStorage.setItem('darkMode', this.isDarkMode.toString());
    this.cambiarModo();
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
