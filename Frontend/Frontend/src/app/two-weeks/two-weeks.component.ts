import { Component, Input } from '@angular/core';
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

  constructor(private service: ForecastService) {
    
  }
  ngOnInit(): void {
    this.service.findTwoWeeksForecast(this.city).subscribe({
      next: value=>{
        this.twoWeeksForecast=value;
        this.days=this.twoWeeksForecast.days;
      }
    })
  }
}
