import { Component} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from "./footer/footer.component";
import { HeaderComponent } from "./header/header.component";
import { VideoComponent } from "./video/video.component";
import { PresentComponent } from "./filter/present/present.component";
import { FilterComponent } from "./filter/filter.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent, VideoComponent, PresentComponent, FilterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title='RainCheck';
  
}
