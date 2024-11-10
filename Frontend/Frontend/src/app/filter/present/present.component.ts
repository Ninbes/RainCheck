import { Component, inject, Input, input, OnInit, signal } from '@angular/core';
import { fakeData } from '../../fakeData';
import { DatePipe } from '@angular/common';
import { FilterComponent } from '../filter.component';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface CityData {
  id: string;
  city: string;
  temperature: string;
}

@Component({
  selector: 'app-present',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './present.component.html',
  styleUrl: './present.component.css',
})
export class PresentComponent{

  /*currentDate=new Date();
  @Input() city: string='Budapest';*/

  @Input() city: string = '';
  temperature: string = '??';
  currentDate: Date = new Date();

  ngOnChanges(): void {
    this.updateTemperature();
  }

  updateTemperature(): void {
    /*const cityData = this.getTemperatureData().find(item => item.city.toLowerCase() === this.city.toLowerCase());
    this.temperature = cityData ? cityData.temperature : '??';*/
    this.getTemperatureData().subscribe(data => {
      const cityData = data.find((item: CityData) => item.city.toLowerCase() === this.city.toLowerCase());
      this.temperature = cityData ? cityData.temperature : '??';
    });
  }

  constructor(private http: HttpClient) {}
  getTemperatureData() {
    /*return [
      { id: '1', city: 'Budapest', temperature: '16' },
      { id: '2', city: 'Debrecen', temperature: '12' }
    ];*/
    return this.http.get<CityData[]>('http://localhost:3000/data');
  }
}
