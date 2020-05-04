import { Component, OnInit } from '@angular/core';
import { Review } from 'src/app/models/review';

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  newReview: Review = new Review;

  constructor() { }

  ngOnInit(): void {
    this.newReview.active = true;
    this.newReview.reviewRatings[0].ratingId = 1;
    this.newReview.reviewRatings[1].ratingId = 2;
    this.newReview.reviewRatings[2].ratingId = 3;
    this.newReview.reviewRatings[3].ratingId = 4;

  }

}
