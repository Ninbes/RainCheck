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
    //console.log(this.enteredCity);
  }

 
  enteredCity!: string;
  selectedForecast:string='1';
  @Output() citySelected = new EventEmitter<string>(); //plusz
  @Output() valueSelected = new EventEmitter<string>();
  onSubmit(){
    this.citySelected.emit(this.enteredCity);//plusz
    this.valueSelected.emit(this.selectedForecast);
    console.log(this.enteredCity);
  }
  onForecastChange() {
    // Frissítheted a tartalmat itt, ha szükséges
    // További logikát is hozzáadhatsz, ha szükséges
    this.valueSelected.emit(this.selectedForecast);
    console.log("Előrejelzés hossza változott:", this.selectedForecast);
  }
}
