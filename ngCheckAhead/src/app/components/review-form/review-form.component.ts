import { Component, OnInit, Input } from '@angular/core';
import { Review } from 'src/app/models/review';
import { ReviewService } from 'src/app/services/review.service';
import { Router } from '@angular/router';
import { ReviewRating } from 'src/app/models/review-rating';
import { Location } from 'src/app/models/location';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  @Input() newReview: Review;
  @Input() location: Location;
  ratingValues = [];

  constructor(
    private router: Router,
    private svc: ReviewService
  ) { }

  ngOnInit(): void {
    // this.newReview.active = true;
    // this.newReview.reviewRatings[0] = new ReviewRating;

  }

  postReview(){
    this.svc.create(this.newReview, this.ratingValues, this.location).subscribe(
      data => {
        console.log('ReviewComponent.create(): Review created.');
        this.router.navigateByUrl('/locations/'+ this.newReview.location.id)
      },
      err => {
        console.error('ReviewComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }
}
