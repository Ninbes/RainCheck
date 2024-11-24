import { Component, inject, Input, input, NgModule, OnInit, signal, SimpleChange, SimpleChanges } from '@angular/core';
import { fakeData } from '../../fakeData';
import { CommonModule, DatePipe } from '@angular/common';
import { FilterComponent } from '../filter.component';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Day, Forecast } from './present.model';
import { ForecastService } from '../../forecast.service';
import { error } from 'console';

interface CityData {
  id: string;
  city: string;
  temperature: string;
}

@Component({
  selector: 'app-present',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './present.component.html',
  styleUrl: './present.component.css',
})
export class PresentComponent{


  @Input() city!: string;
  /*temperature: string = '??';
  currentDate: Date = new Date();

  ngOnChanges(): void {
    this.updateTemperature();
  }

  updateTemperature(): void {
    this.getTemperatureData().subscribe(data => {
      const cityData = data.find((item: CityData) => item.city.toLowerCase() === this.city.toLowerCase());
      this.temperature = cityData ? cityData.temperature : '??';
    });
  }

  constructor(private http: HttpClient) {}
  getTemperatureData() {
    return this.http.get<CityData[]>('http://localhost:3000/data');
  }*/
    forecast!: Forecast;
    days!: Day[];
    errorMessage: string | null=null;

    constructor(private service: ForecastService) {
    
    }
    ngOnInit(): void {
      this.service.findForecast(this.city).subscribe({
        next: value=>{
          this.forecast=value;
          //this.hours=this.day.hours;
          this.days=this.forecast.days;
          this.errorMessage=null;
        },
        error: (error)=> {
          this.errorMessage=error.message;
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
  
}
