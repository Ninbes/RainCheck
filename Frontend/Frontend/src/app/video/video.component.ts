import { Component } from '@angular/core';

@Component({
  selector: 'app-video',
  standalone: true,
  imports: [],
  templateUrl: './video.component.html',
  styleUrl: './video.component.css',
  host:{
    '(window:scroll)': 'onWindowScroll()'
  }
})
export class VideoComponent {

  opacity=1;
  
  onWindowScroll() {
    const maxScroll = document.body.scrollHeight - window.innerHeight;
    const scrollPosition = window.scrollY;
    this.opacity = 1 - (scrollPosition / maxScroll);
}
}
