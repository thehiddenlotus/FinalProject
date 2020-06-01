import { User } from 'src/app/models/user';
import { ReviewRatingId } from './../../models/review-rating-id';
import { AverageRatings } from './../../models/average-ratings';
import { ReviewService } from 'src/app/services/review.service';
import { Router } from '@angular/router';
import { LocationService } from './../../services/location.service';
import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { Location } from 'src/app/models/location';
import { Review } from 'src/app/models/review';
import { ReviewRating } from 'src/app/models/review-rating';
import { ReviewRatingService } from 'src/app/services/review-rating.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit, OnChanges {

  @Input() searchKey: string;

  favorites: Location[];
  reviewRatings: ReviewRating[];

  cleanlinessAvg: number;
  trafficAvg: number;
  checkoutAvg: number;
  stockAvg: number;


  constructor(
    private auth: AuthService,
    private locSvc: LocationService,
    private rrServ: ReviewRatingService,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.locSvc.index().subscribe(
      data => {
        this.favorites = data;
        this.getAverages();
      },
      error => {
        console.log("error in favorites index");
        console.log(error);

      }
    )
  }

  showDetail(id: number) {
    this.router.navigateByUrl('locations/' + id);
  }

  login() {
    this.router.navigateByUrl('login');
  }

  register() {
    this.router.navigateByUrl('register');
  }

  ngOnChanges(changes: SimpleChanges) {
    for (let propName in changes) {
      if (propName === 'searchKey') {
        console.log('search submitted')
      }
    }
  }

  //function to populate reviewRatings to get averages
  populateReviewRatings(id: number, j: number): void {
    this.rrServ.findByLocation(id).subscribe(
      good => {
        this.reviewRatings = good;
        this.populateAverages(this.reviewRatings, j)
      },
      error => {
        console.log("error in populating reviewRatings in location-detail");
        console.log(error);
        this.populateAverages(this.reviewRatings, j)
      }
    )
  }

  //function to get averages for current store
  getAverages(): void {

    //go through values and add to arrays
    for (let j = 0; j < this.favorites.length; j++) {
      this.populateReviewRatings(this.favorites[j].id, j);
    }
  }

  populateAverages(reviews: ReviewRating[], id:number){
    var cleanlinessA = [];
    var trafficA = [];
    var checkoutA = [];
    var stockA = [];
    if (this.reviewRatings.length > 0) {
      console.log('inside if')
      for (let i = 0; i < this.reviewRatings.length; i++) {
        if (this.reviewRatings[i].id.ratingId === 1) {
          cleanlinessA.push(this.reviewRatings[i].ratingValue);
        }
        else if (this.reviewRatings[i].id.ratingId === 2) {
          trafficA.push(this.reviewRatings[i].ratingValue);
        }
        else if (this.reviewRatings[i].id.ratingId === 3) {
          checkoutA.push(this.reviewRatings[i].ratingValue);
        }
        else if (this.reviewRatings[i].id.ratingId === 4) {
          stockA.push(this.reviewRatings[i].ratingValue);
        }
      }


      //calculate avg or and return else zero
      let rating = new AverageRatings();
      let sum = cleanlinessA.length > 0 ? cleanlinessA.reduce((previous, current) => current += previous) : 0;
      rating.cleanliness = cleanlinessA.length > 0 ? sum / cleanlinessA.length : 0;
      sum = trafficA.length > 0 ? trafficA.reduce((previous, current) => current += previous) : 0;
      rating.traffic = trafficA.length > 0 ? sum / trafficA.length : 0;
      sum = checkoutA.length > 0 ? checkoutA.reduce((previous, current) => current += previous) : 0;
      rating.checkout = checkoutA.length > 0 ? sum / checkoutA.length : 0;
      sum = stockA.length > 0 ? stockA.reduce((previous, current) => current += previous) : 0;
      rating.stock = stockA.length > 0 ? sum / stockA.length : 0;
      this.favorites[id].averageRatings = rating;
      console.log(this.favorites[id].averageRatings)

    }
    else {
      let rating = new AverageRatings();
      rating.cleanliness = 0;
      rating.traffic = 0;
      rating.checkout = 0;
      rating.stock = 0;
      this.favorites[id].averageRatings = rating;
    }
    // console.log(this.favorites[id])
  }

  public completionPercent(num: number): String{
    return Math.round((num * 10)) + '';
  }

  getBackgroundColor(ratingNumber) {
    // console.log('The rating number is ' + ratingNumber);
    let color = 'gray';
    if (ratingNumber <= 5) {
      color = '#f94144'
    }
    else if(ratingNumber <= 6.5) {
        color = '#f3722c';
      }
    else if(ratingNumber <= 8) {
        color = '#90be6d';
      }
   else if (ratingNumber <= 10) {
        color = '#43aa8b';
      }
    return color;
  }

  checkLogin(): boolean {
    return this.auth.checkLogin();
  }

}



