import { Component, OnInit, Input } from '@angular/core';
import { Review } from 'src/app/models/review';
import { Location } from 'src/app/models/location';
import { Router } from '@angular/router';
import { ReviewService } from 'src/app/services/review.service';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-edit-review-form',
  templateUrl: './edit-review-form.component.html',
  styleUrls: ['./edit-review-form.component.css']
})
export class EditReviewFormComponent implements OnInit {

  @Input() editReview: Review;
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

  postReview(){

    this.svc.update(this.editReview).subscribe(
      data => {
        console.log('ReviewComponent.create(): Review created.');
        this.locSvc.show(this.location.id);
      },
      err => {
        console.error('ReviewComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }
}
