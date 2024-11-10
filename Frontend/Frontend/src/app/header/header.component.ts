import { Component, HostListener, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  host:{
    '(window:scroll)': 'onWindowScroll()'
  }
})
export class HeaderComponent {
  isScrolled = false;

  // Figyeli a window görgetését
  //@HostListener('window:scroll', [])
  onWindowScroll() {
    // Ellenőrzi, hogy a görgetési pozíció nagyobb-e mint 50px
    this.isScrolled = window.scrollY > 25;
  }

}
