import { Component, EventEmitter, HostListener, Output, ViewEncapsulation } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  host:{
    '(window:scroll)': 'onWindowScroll()'
  }
})
export class HeaderComponent {
  isScrolled = false;
  colorValue: string="rainy";
  @Output() selectedColor = new EventEmitter<string>();

  onWindowScroll() {
    this.isScrolled = window.scrollY > 25;
  }

  onColorChange() {
    this.selectedColor.emit(this.colorValue)
    console.log("szín változott:", this.colorValue);
  }


}
