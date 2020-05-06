import { TrafficDetail } from './../../models/traffic-detail';
import { PopulartimesData } from './../../models/populartimes-data';
import { TrafficData } from './../../models/traffic-data';
import { TrafficDataService } from './../../services/traffic-data.service';
import { UserService } from './../../services/user.service';
import { ReviewService } from './../../services/review.service';
import { ReviewRating } from './../../models/review-rating';
import { ReviewRatingService } from './../../services/review-rating.service';
import { Component, OnInit, Optional } from '@angular/core';
import { LocationService } from './../../services/location.service';
import { Location } from 'src/app/models/location';
import { ActivatedRoute, Router } from "@angular/router";



import { NgModule } from '@angular/core'; //Ch
import { BrowserModule } from '@angular/platform-browser';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { multi } from './data';
import { Review } from 'src/app/models/review';
import { Rating } from 'src/app/models/rating';
import { Comment } from 'src/app/models/comment';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { ReviewCommentService } from 'src/app/services/review-comment.service';

@Component({
  selector: 'app-location-detail',
  templateUrl: './location-detail.component.html',
  styleUrls: ['./location-detail.component.css']
})
export class LocationDetailComponent implements OnInit {

  //code for heat map
  multi: any[];
  newMulti: any[];
  view: any[] = [700, 200];

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

  //F I E L D S
  ratingReviewSelected = false;
  ratingReviewSelected1 = false;
  ratingReviewSelected2 = false;

  location: Location;
  reviewRatings: ReviewRating[];
  reviews: Review[];
  cleanlinessAvg: number;
  trafficAvg: number;
  checkoutAvg: number;
  stockAvg: number;
  urlParam = parseInt(this.route.snapshot.paramMap.get("id"));
  urlId = +this.urlParam;
  newReview: Review = null;
  editReview: Review = null;
  newComment: Comment = null;
  editComment: Comment = null;
  editLoc: Location = null;

  popTimes: TrafficData;
  jsonTimes: TrafficDetail;

  currentUser: User = null;
  userId = null;
  opt: Optional = null;

  //M E T H O D S
  constructor(
    private locSvc: LocationService,
    private userSvc: UserService,
    private comSvc: ReviewCommentService,
    private auth: AuthService,
    private rrServ: ReviewRatingService,
    private route: ActivatedRoute,
    private reviewServ: ReviewService,
    private trafficServ: TrafficDataService,
    private router: Router
  ) {
    Object.assign(this, { multi });//for chart
  }

  ngOnInit(): void {
    this.locSvc.show(this.urlId).subscribe(
      data => {
        this.location = data;
        this.populateReviewRatings(this.urlId);
        this.populateReviews(this.urlId);
      },
      error => {
        console.log("error in location data for location-detail");
        console.log(error);
      }
    )
    this.userId = this.auth.getCurrentUserId();

    this.userSvc.show(this.userId).subscribe(
      success => {
        this.currentUser = success;
        console.log(this.currentUser);

      },
      fail => {
        console.log("no user logged in");

      }
    )
  }

  reload() {
    window.location.reload();
  }

  addReview() {
    this.newReview = new Review();
    this.newReview.location = this.location;
    this.newReview.user = this.currentUser;
  }

  updateReview(review: Review) {
    this.editReview = review;
  }

  deleteReview(review: Review) {
    console.log(review);

    console.log(review.comments);
    console.log(review.reviewRatings);

    this.reviewServ.destroy(review.id).subscribe(
      data => {
        console.log("review deleted");
        this.reload();
      },
      err => {
        console.log("error in locationdetail deleteReview");
        console.log(err);
      }
    )
  }


  addComment(review: Review) {
    this.newComment = new Comment();
    this.newComment.user = this.currentUser;
    this.newComment.review = review;
  }

  updateComment(comment: Comment) {
    this.editComment = comment;
  }

  deleteComment(comment: Comment) {
    this.comSvc.destroy(comment.id).subscribe(
      data => {
        console.log("comment deleted");
        this.reload();
      },
      err => {
        console.log("error in locationdetail deleteComment");
        console.log(err);
      }
    )
  }

  updateLoc(location: Location) {
    this.editLoc = location;
  }
  //function to populate reviewRatings to get averages
  populateReviewRatings(id: number): void {
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
    var cleanliness = [];
    var traffic = [];
    var checkout = [];
    var stock = [];
    //go through values and add to arrays
    for (let i = 0; i < this.reviewRatings.length; i++) {
      if (this.reviewRatings[i].id.ratingId === 1) {
        cleanliness.push(this.reviewRatings[i].ratingValue);
      }
      else if (this.reviewRatings[i].id.ratingId === 2) {
        traffic.push(this.reviewRatings[i].ratingValue);
      }
      else if (this.reviewRatings[i].id.ratingId === 3) {
        checkout.push(this.reviewRatings[i].ratingValue);
      }
      else if (this.reviewRatings[i].id.ratingId === 4) {
        stock.push(this.reviewRatings[i].ratingValue);
      }
    }
    //calculate avg or and return else zero
    let sum = cleanliness.length > 0 ? cleanliness.reduce((previous, current) => current += previous) : 0;
    this.cleanlinessAvg = cleanliness.length > 0 ? sum / cleanliness.length : 0;
    sum = traffic.length > 0 ? traffic.reduce((previous, current) => current += previous) : 0;
    this.trafficAvg = traffic.length > 0 ? sum / traffic.length : 0;
    sum = checkout.length > 0 ? checkout.reduce((previous, current) => current += previous) : 0;
    this.checkoutAvg = checkout.length > 0 ? sum / checkout.length : 0;
    sum = stock.length > 0 ? stock.reduce((previous, current) => current += previous) : 0;
    this.stockAvg = stock.length > 0 ? sum / stock.length : 0;
  }

  //function to populate reviews
  populateReviews(id: number): void {
    this.reviewServ.getReviewsByLocationId(id).subscribe(
      good => {
        this.reviews = good;
        this.populateTransitData();
      },
      error => {
        console.log("error in populating reviews in location-detail");
        console.log(error);
      }
    )
  }

  //function to populate transit data
  populateTransitData(): void {
    this.trafficServ.getTransitData(this.location.googleId).subscribe(
      good => {
        this.popTimes = good;
        for (let i = 0; i < this.popTimes.populartimes.length; i++) {
          for (let index = 7, h = 0; h < multi.length; index++, h++) {
            multi[h].series[i].value = this.popTimes.populartimes[i].data[index];
          }
        }
        console.log(multi)
        this.newMulti = multi;
      },
      error => {
        console.log("error in populating transit data");
        console.log(error);
      }
    )
  }
}
