import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { AddressService } from 'src/app/services/address.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-change-address',
  templateUrl: './change-address.component.html',
  styleUrls: ['./change-address.component.css']
})
export class ChangeAddressComponent implements OnInit {

  changeAddress: Address = new Address();

  constructor(
    private auth: AuthService,
    private router: Router,
    private addSvc: AddressService,
    private userSvc: UserService
  ) { }

  ngOnInit(): void {
    const userId = this.auth.getCurrentUserId();
    console.log("Current user id is " + userId);

    if (userId) {
      this.loadAddress(userId);
    }
  }

  updateAddress(address: Address) {
    console.log(address);

    this.addSvc.update(address).subscribe(
      success => {
        this.router.navigateByUrl('/search');
      },
      fail => {

      }
    )
  }
  loadAddress(id) {
    this.userSvc.show(id).subscribe(
      user => {
        this.changeAddress = user.address;
      },
      fail => {

      }
    )
  }

}
