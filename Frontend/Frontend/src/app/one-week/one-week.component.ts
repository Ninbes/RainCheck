import { Component, Input, OnInit } from '@angular/core';
import { OneWeekForecast, Day } from './one-week.model';
import { ForecastService } from '../forecast.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-one-week',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './one-week.component.html',
  styleUrl: './one-week.component.css'
})
export class OneWeekComponent implements OnInit{
  oneWeekForecast!: OneWeekForecast;
  days!: Day[];
  @Input() city!:string;

  constructor(private service: ForecastService) {
    
  }
  ngOnInit(): void {
    this.service.findOneWeekForecast(this.city).subscribe({
      next: value=>{
        this.oneWeekForecast=value;
        this.days=this.oneWeekForecast.days;
      }
    })
  }
  expandedCards: { [key: string]: boolean } = {};

    toggleDetails(index: number): void {
      this.expandedCards[index] = !this.expandedCards[index]; // Az adott index állapotának váltása
    }
}
