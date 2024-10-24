import { Component, OnInit, signal } from '@angular/core';
import { fakeData } from '../fakeData';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-present',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './present.component.html',
  styleUrl: './present.component.css',
})
export class PresentComponent{

  data=fakeData[0];
  currentDate=new Date();
  
}
