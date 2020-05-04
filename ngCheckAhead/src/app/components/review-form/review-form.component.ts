import { Component, OnInit, Input } from '@angular/core';
import { Review } from 'src/app/models/review';
import { ReviewService } from 'src/app/services/review.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  @Input() newReview: Review = new Review;

  constructor(
    private router: Router,
    private svc: ReviewService
  ) { }

  ngOnInit(): void {
    this.newReview.active = true;
    this.newReview.reviewRatings[0].ratingId = 1;
    this.newReview.reviewRatings[1].ratingId = 2;
    this.newReview.reviewRatings[2].ratingId = 3;
    this.newReview.reviewRatings[3].ratingId = 4;

  }

  postReview(){
    this.svc.create(this.newReview).subscribe(
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
