import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-instrucciones',
  standalone: true,
  imports: [FooterComponent, HeaderComponent],
  templateUrl: './instrucciones.component.html',
  styleUrl: './instrucciones.component.css'
})
export class InstruccionesComponent {

  constructor(private router:Router) {  }

  ngOnInit(): void{
  }

  toMain(){
    this.router.navigate([""]);
  }
}
