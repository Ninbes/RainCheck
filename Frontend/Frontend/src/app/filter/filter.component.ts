import { NgClass } from '@angular/common';
import { Component, EventEmitter, Output, output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PresentComponent } from "./present/present.component";
@Component({
  selector: 'app-filter',
  standalone: true,
  imports: [FormsModule, NgClass],
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {
  
  constructor(){}
  onSearch(){
    console.log("na");
  }

 
  enteredCity!: string;
  selectedForecast:string='1';
  @Output() citySelected = new EventEmitter<string>();
  @Output() valueSelected = new EventEmitter<string>();
  onSubmit(){
    this.citySelected.emit(this.enteredCity);
    this.valueSelected.emit(this.selectedForecast);
    console.log(this.enteredCity);
  }
  onForecastChange() {
    this.valueSelected.emit(this.selectedForecast);
    console.log("Előrejelzés hossza változott:", this.selectedForecast);
  }
}
