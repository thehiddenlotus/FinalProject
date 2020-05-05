import { AddressService } from './../../services/address.service';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  newAddress: Address = new Address();

  constructor(
    private auth: AuthService,
    private addsvc: AddressService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register() {
    console.log(this.newAddress, this.newUser);
    this.newUser.address = this.newAddress;
    this.auth.register(this.newUser).subscribe(
      data => {
        console.log('RegisterComponent.register(): user registered.');
        this.auth.login(this.newUser.username, this.newUser.password).subscribe(
          next => {
            console.log('RegisterComponent.register(): user logged in, routing to /home.');
            this.router.navigateByUrl('/search');
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      },
      err => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
      }
    );
  }

}
