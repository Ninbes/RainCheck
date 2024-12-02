import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
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
  errorMessage: string | null=null;

  constructor(private service: ForecastService) {
    
  }
  ngOnInit(): void {
    this.service.findOneWeekForecast(this.city).subscribe({
      next: value=>{
        this.oneWeekForecast=value;
        this.days=this.oneWeekForecast.days;
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
    this.service.findOneWeekForecast(this.city).subscribe({
      next: value=>{
        this.oneWeekForecast=value;
        this.days=this.oneWeekForecast.days;
        this.errorMessage=null;
        },
        error: (error)=> {
          this.errorMessage=error.message;
        }
    })
  }
  expandedCards: { [key: string]: boolean } = {};

    toggleDetails(index: number): void {
      this.expandedCards[index] = !this.expandedCards[index];
    }
}
