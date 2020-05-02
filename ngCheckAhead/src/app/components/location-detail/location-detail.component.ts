import { Component, OnInit } from '@angular/core';

import { NgModule } from '@angular/core'; //Ch
import { BrowserModule } from '@angular/platform-browser';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { multi } from './data';

@Component({
  selector: 'app-location-detail',
  templateUrl: './location-detail.component.html',
  styleUrls: ['./location-detail.component.css']
})
export class LocationDetailComponent implements OnInit {
//for chart
  multi: any[];
  view: any[] = [700, 100];

  // options
  legend: boolean = false;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = false;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Store Times';
  yAxisLabel: string = 'Year';
  legendPosition: string = 'below';

  colorScheme = {
    domain: ['#D6E3CD', '#60C464', '#60C464', '#4486B5', '#4486B5',
    '#ED7D1D']
  };

  onSelect(data): void {
    console.log('Item clicked', JSON.parse(JSON.stringify(data)));
  }
  
  onActivate(data): void {
    console.log('Activate', JSON.parse(JSON.stringify(data)));
  }

  onDeactivate(data): void {
    console.log('Deactivate', JSON.parse(JSON.stringify(data)));
  }
  ////////////


  ratingReviewSelected = false;
  ratingReviewSelected1 = false;
  ratingReviewSelected2 = false;

  constructor() {
    Object.assign(this, { multi });//for chart
   }

  ngOnInit(): void {
  }

}
