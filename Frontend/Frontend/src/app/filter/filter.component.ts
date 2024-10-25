import { NgClass } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, NgClass],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {
  
  onSearch(){
    console.log("na");
  }

  isSelected: boolean = false;

  selectOption() {
    this.isSelected = !this.isSelected; // Csak példának, állítsd logikád szerint
  }
}
