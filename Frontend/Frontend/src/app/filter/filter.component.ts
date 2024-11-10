import { NgClass } from '@angular/common';
import { Component, EventEmitter, Output, output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PresentComponent } from "./present/present.component";
@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [FormsModule, NgClass, PresentComponent],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {
  
  constructor(){}
  onSearch(){
    console.log("na");
    //console.log(this.enteredCity);
  }

  isSelected: boolean = true;

  selectOption() {
    this.isSelected = !this.isSelected; // Csak példának, állítsd logikád szerint
  }
  enteredCity: string='Budapest';
  selectedForecast='1';
  @Output() citySelected = new EventEmitter<string>(); //plusz
  onSubmit(){
    this.citySelected.emit(this.enteredCity);//plusz
    console.log(this.enteredCity);
  }
}
