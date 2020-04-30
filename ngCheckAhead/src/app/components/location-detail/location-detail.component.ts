import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-location-detail',
  templateUrl: './location-detail.component.html',
  styleUrls: ['./location-detail.component.css']
})
export class LocationDetailComponent implements OnInit {

  ratingReviewSelected = false;
  ratingReviewSelected1 = false;
  ratingReviewSelected2 = false;

  constructor() { }

  ngOnInit(): void {
  }

}
