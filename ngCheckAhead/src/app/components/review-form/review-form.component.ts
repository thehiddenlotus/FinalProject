import { Component, OnInit, Input } from '@angular/core';
import { Review } from 'src/app/models/review';
import { ReviewService } from 'src/app/services/review.service';
import { Router } from '@angular/router';
import { ReviewRating } from 'src/app/models/review-rating';
import { Location } from 'src/app/models/location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  @Input() newReview: Review;
  @Input() location: Location;
  ratingValues = [5,5,5,5];

  constructor(
    private router: Router,
    private svc: ReviewService,
    private locSvc: LocationService
  ) { }

  ngOnInit(): void {
    // this.newReview.active = true;
    // this.newReview.reviewRatings[0] = new ReviewRating;

  }

  reload() {
    window.location.reload();
  }

  postReview(){
    this.svc.create(this.newReview, this.ratingValues, this.location.id).subscribe(
      data => {
        console.log('ReviewComponent.create(): Review created.');
      },
      err => {
        console.error('ReviewComponent.create(): ERROR.');
        console.error(err);
      }
      );
    this.reload();
  }
}
