import { Component, OnInit, Input } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';
import { Location } from 'src/app/models/location';
import { AddressService } from 'src/app/services/address.service';
import { Address } from 'src/app/models/address';
import { Router } from '@angular/router';

@Component({
  selector: 'app-location-form',
  templateUrl: './location-form.component.html',
  styleUrls: ['./location-form.component.css'],
})
export class LocationFormComponent implements OnInit {
  @Input() newLoc: Location;
  newAdd: Address = new Address();

  constructor(
    private svc: LocationService,
    private addSvc: AddressService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  load(id) {
    this.router.navigateByUrl('locations/' + id);
  }

  postLocation() {
    console.log(this.newAdd);
    console.log(this.newLoc);

    this.addSvc.create(this.newAdd).subscribe(
      (data) => {
        console.log(
          'LocationAddressComponent.create(): LocationAddress created.'
        );
        this.newLoc.address = data;
        console.log('now trying to get id');

        this.svc.getId(this.newLoc).subscribe(
          (pass) => {
            console.log('LocationComponent.getId(): google id retrieved.');
            console.log(pass);

            this.newLoc.googleId = pass.results[0].place_id;
            console.log("new ID = " + this.newLoc.googleId);

            this.svc.create(this.newLoc).subscribe(
              (yay) => {
                console.log('LocationComponent.create(): Location created.');
                this.svc.show(yay.id);
                this.load(yay.id);
              },
              (nay) => {
                console.error('LocationComponent.create(): ERROR.');
                console.error(nay);
              }
            );
          },
          (fail) => {
            console.error('LocationComponent.getId(): ERROR.');
            console.error(fail);
          }
        );
      },
      (err) => {
        console.error('LocationAddressComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }
}
