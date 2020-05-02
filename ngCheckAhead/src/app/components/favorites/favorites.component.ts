import { Router } from '@angular/router';
import { LocationService } from './../../services/location.service';
import { Component, OnInit } from '@angular/core';
import { Location } from 'src/app/models/location';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {

  favorites: Location[];

  constructor(
    private locSvc: LocationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.locSvc.index().subscribe(
      data => {
        this.favorites = data;
      },
      error => {
        console.log("error in favorites index");
        console.log(error);

      }
    )
  }

  showDetail(id: number){
    this.router.navigateByUrl('locations/'+id);
  }

}
