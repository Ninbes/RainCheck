import { Component, Input, SimpleChanges } from '@angular/core';
import { Day, TwoWeeksForecast } from './two-weeks.model';
import { ForecastService } from '../forecast.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-two-weeks',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './two-weeks.component.html',
  styleUrl: './two-weeks.component.css'
})
export class TwoWeeksComponent {
  twoWeeksForecast!: TwoWeeksForecast;
  days!: Day[];
  @Input() city!:string;
  errorMessage: string | null=null;

  constructor(private service: ForecastService) {
    
  }
  ngOnInit(): void {
    this.service.findTwoWeeksForecast(this.city).subscribe({
      next: value=>{
        this.twoWeeksForecast=value;
        this.days=this.twoWeeksForecast.days;
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
    this.service.findTwoWeeksForecast(this.city).subscribe({
      next: value=>{
        this.twoWeeksForecast=value;
        this.days=this.twoWeeksForecast.days;
        this.errorMessage=null;
        },
        error: (error)=> {
          this.errorMessage=error.message;
        }
    })
  }
  expandedCards: { [key: string]: boolean } = {};

    toggleDetails(index: number): void {
      this.expandedCards[index] = !this.expandedCards[index]; // Az adott index állapotának váltása
    }
}
