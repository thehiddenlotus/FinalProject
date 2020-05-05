import { Component, OnInit, Input } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';
import { Location } from 'src/app/models/location';
import { AddressService } from 'src/app/services/address.service';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-location-form',
  templateUrl: './location-form.component.html',
  styleUrls: ['./location-form.component.css']
})
export class LocationFormComponent implements OnInit {

  @Input() newLoc: Location;
  newAdd: Address = new Address();

  constructor(
    private svc: LocationService,
    private addSvc: AddressService
  ) { }

  ngOnInit(): void {
  }

  postLocation(){
    console.log(this.newAdd);
    console.log(this.newLoc);


    this.addSvc.create(this.newAdd).subscribe(
      data => {
        console.log('LocationAddressComponent.create(): LocationAddress created.');
        this.newLoc.address = data;
        this.svc.create(this.newLoc).subscribe(
          yay => {
            console.log('LocationComponent.create(): Location created.');
            this.svc.show(yay.id);
          },
          nay => {
            console.error('LocationComponent.create(): ERROR.');
            console.error(nay);
          }
        );
      },
      err => {
        console.error('LocationAddressComponent.create(): ERROR.');
        console.error(err);
      }
    );
  }
}
