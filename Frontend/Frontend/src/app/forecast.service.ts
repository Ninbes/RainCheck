import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Forecast } from './forecast/forecast.model';
import { OneWeekForecast } from './one-week/one-week.model';
import { TwoWeeksForecast } from './two-weeks/two-weeks.model';

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
  findOneWeekForecast(city: string):Observable<OneWeekForecast>{
    return this.httpClient.get<OneWeekForecast>(`${this.rootUrl}/one-week?location=${city}`)
  }
  findTwoWeeksForecast(city: string):Observable<TwoWeeksForecast>{
    return this.httpClient.get<TwoWeeksForecast>(`${this.rootUrl}/two-week?location=${city}`)
  }
}
