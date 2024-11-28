import { Component, Input, NgModule, OnInit, SimpleChanges} from '@angular/core';
import { Day, Forecast, Hour } from './forecast.model';
import { ForecastService } from '../forecast.service';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-forecast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './forecast.component.html',
  styleUrl: './forecast.component.css'
})
export class ForecastComponent implements OnInit{
forecast!: Forecast;
days!: Day[];

hours!: Hour[];

@Input() city!:string;

  constructor(private service: ForecastService) {
    
  }
  ngOnInit(): void {
    this.service.findForecast(this.city).subscribe({
      next: value=>{
        this.forecast=value;
        //this.hours=this.day.hours;
        this.days=this.forecast.days;
        
      }
    })
  }
  ngOnChanges(changes: SimpleChanges){
    if(changes['city']){
      this.loadForecast();
    }
  }
  loadForecast(){
    this.service.findForecast(this.city).subscribe({
      next: value=>{
        this.forecast=value;
        this.days=this.forecast.days;
      }
    })
  }
  /*showDetails: boolean=false;
  show(){
    this.showDetails=!this.showDetails;
  }*/
    expandedCards: { [key: string]: boolean } = {};

    toggleDetails(index: number): void {
      this.expandedCards[index] = !this.expandedCards[index]; // Az adott index állapotának váltása
    }
}