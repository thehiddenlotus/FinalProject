import { Component, OnInit } from '@angular/core';
import { multiHome } from './data';
import { NgModule } from '@angular/core'; //Ch
import { BrowserModule } from '@angular/platform-browser';
import { NgxChartsModule } from '@swimlane/ngx-charts';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  multiHome: any[];
  searchKeyword: string;
  public innerWidth: any;
  constructor() { 
    Object.assign(this, { multiHome });//for chart
  }

  ngOnInit(): void {
    this.innerWidth = window.innerWidth;
    console.log(this.innerWidth);
  }
  
   //code for heat map
   multi: any[];
   newMulti: any[];
   view: any[] = [500, 300];
 
   // options
   legend: boolean = false;
   showLabels: boolean = true;
   animations: boolean = true;
   xAxis: boolean = true;
   yAxis: boolean = true;
   showYAxisLabel: boolean = false;
   showXAxisLabel: boolean = false;
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
   ////////////end for stuff for chart

}
