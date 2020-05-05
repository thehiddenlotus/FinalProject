import { Component, OnInit, Input } from '@angular/core';
import { Address } from 'src/app/models/address';
import { LocationService } from 'src/app/services/location.service';
import { AddressService } from 'src/app/services/address.service';
import { Location } from 'src/app/models/location';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-location-form',
  templateUrl: './edit-location-form.component.html',
  styleUrls: ['./edit-location-form.component.css']
})
export class EditLocationFormComponent implements OnInit {

  @Input() location: Location;
  newAdd: Address = new Address();

  constructor(
    private svc: LocationService,
    private addSvc: AddressService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  reload() {
    window.location.reload();
  }

  postLocation(){
    // this.addSvc.update(this.newAdd).subscribe(
    //   data => {
    //     console.log('LocationAddressComponent.create(): LocationAddress created.');
    //     this.location.address = data;
        this.svc.update(this.location).subscribe(
          yay => {
            console.log('LocationComponent.create(): Location created.');
            this.svc.show(yay.id);
            this.reload();
          },
          nay => {
            console.error('LocationComponent.create(): ERROR.');
            console.error(nay);
          }
        );
    //   },
    //   err => {
    //     console.error('LocationAddressComponent.create(): ERROR.');
    //     console.error(err);
    //   }
    // );
  }

  removeLocation(){
    this.svc.destroy(this.location.id).subscribe(
      data => {
        console.log('LOCATION DELETED');
        this.router.navigateByUrl('/search');
      },
      err => {
        console.error(err);
      }

    )
  }
}
