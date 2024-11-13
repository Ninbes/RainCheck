import { Component, NgModule, OnInit} from '@angular/core';
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

  constructor(private service: ForecastService) {
    
  }
  ngOnInit(): void {
    this.service.findForecast("debrecen").subscribe({
      next: value=>{
        this.forecast=value;
        //this.hours=this.day.hours;
        this.days=this.forecast.days;
      }
    })
  }
  
}