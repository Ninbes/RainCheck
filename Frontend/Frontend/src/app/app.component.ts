import { Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from "./footer/footer.component";
import { HeaderComponent } from "./header/header.component";
import { VideoComponent } from "./video/video.component";
import { PresentComponent } from "./filter/present/present.component";
import { FilterComponent } from "./filter/filter.component";
import { ForecastComponent } from "./forecast/forecast.component";
import { OneWeekComponent } from "./one-week/one-week.component";
import { TwoWeeksComponent } from "./two-weeks/two-weeks.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent, VideoComponent, PresentComponent, FilterComponent, ForecastComponent, OneWeekComponent, TwoWeeksComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',

host:{
  '(window:scroll)': 'onWindowScroll()'
}
})
export class AppComponent{
  
  title='RainCheck';
  selectedValue: string="1";
  selectedCity: string="Budapest";

  scrollToSection() {
    const targetElement = document.getElementById('target-section');
    if (targetElement) {
      targetElement.scrollIntoView({ behavior: 'smooth' });
    }
  }
  isScrolled = false;

  onWindowScroll() {
    this.isScrolled = window.scrollY > 25;
  }
}
