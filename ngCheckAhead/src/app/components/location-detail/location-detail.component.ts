import { ReviewRating } from './../../models/review-rating';
import { ReviewRatingService } from './../../services/review-rating.service';
import { Component, OnInit } from '@angular/core';
import { LocationService } from './../../services/location.service';
import { Location } from 'src/app/models/location';
import { ActivatedRoute } from "@angular/router";



import { NgModule } from '@angular/core'; //Ch
import { BrowserModule } from '@angular/platform-browser';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { multi } from './data';
import { Review } from 'src/app/models/review';

@Component({
  selector: 'app-location-detail',
  templateUrl: './location-detail.component.html',
  styleUrls: ['./location-detail.component.css']
})
export class LocationDetailComponent implements OnInit {
 
  //code for heat map
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
  ////////////end for stuff for chart


  ratingReviewSelected = false;
  ratingReviewSelected1 = false;
  ratingReviewSelected2 = false;

  location: Location;
  reviewRatings: ReviewRating[];
  cleanlinessAvg: number;
  trafficAvg: number;
  checkoutAvg: number;
  stockAvg: number;
  urlParam = parseInt(this.route.snapshot.paramMap.get("id"));
  urlId = +this.urlParam;

  constructor(
    private locSvc: LocationService,
    private rrServ: ReviewRatingService,
    private route: ActivatedRoute
      ) {
    Object.assign(this, { multi });//for chart
  }

  ngOnInit(): void {
    this.locSvc.show(this.urlId).subscribe(
      data => {
        this.location = data;
        this.populateReviewRatings(this.urlId);
      },
      error => {
        console.log("error in location data for location-detail");
        console.log(error);
      }
    )
  }

  //function to populate reviewRatings to get averages
  populateReviewRatings(id: number): void{
    this.rrServ.findByLocation(id).subscribe(
      good => {
        this.reviewRatings = good;
        this.getAverages();
      },
      error => {
        console.log("error in populating reviewRatings in location-detail");
        console.log(error);
      }
    )
  }

  //function to get averages for current store
  getAverages(): void {
    var cleanliness= [];
    var traffic= [];
    var checkout= [];
    var stock= [];
    //go through values and add to arrays
    for (let i = 0; i < this.reviewRatings.length; i++) {
      if(this.reviewRatings[i].id.ratingId === 1){
          cleanliness.push(this.reviewRatings[i].ratingValue);
      }
      else if(this.reviewRatings[i].id.ratingId === 2){
          traffic.push(this.reviewRatings[i].ratingValue);
      }
      else if(this.reviewRatings[i].id.ratingId === 3){
          checkout.push(this.reviewRatings[i].ratingValue);
      }
      else if(this.reviewRatings[i].id.ratingId === 4){
          stock.push(this.reviewRatings[i].ratingValue);
      }
    }
    //calculate avg or and return else zero
    let sum = cleanliness.length > 0 ?cleanliness.reduce((previous, current) => current += previous):0;
    this.cleanlinessAvg = cleanliness.length > 0 ? sum / cleanliness.length : 0;
    sum = traffic.length > 0 ? traffic.reduce((previous, current) => current += previous):0;
    this.trafficAvg = traffic.length > 0 ? sum / traffic.length : 0;
    sum = checkout.length > 0 ? checkout.reduce((previous, current) => current += previous):0;
    this.checkoutAvg = checkout.length > 0 ? sum / checkout.length : 0;
    sum = stock.length > 0 ? stock.reduce((previous, current) => current += previous):0;
    this.stockAvg = stock.length > 0 ? sum / stock.length : 0;
  }


}
