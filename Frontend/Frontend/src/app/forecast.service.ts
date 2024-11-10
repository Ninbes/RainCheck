import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Forecast } from './forecast/forecast.model';

@Injectable({
  providedIn: 'root'
})
export class ForecastService {
  readonly rootUrl: string;

  constructor(
    private httpClient: HttpClient
  ) {
    this.rootUrl = 'http://localhost:8081/forecast';
  }
  findForecast(city: string):Observable<Forecast>{
    return this.httpClient.get<Forecast>(`${this.rootUrl}/today?location=${city}`)
  }
}
