import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  register(form: NgForm) {
    const user: User = form.value;

    this.auth.register(user).subscribe(
      registered => {
        console.log('Register Component.register(): user registered');
        this.auth.login(user.username, user.password).subscribe(
          loggedIn => {
            console.log('RegisterComponent.register(): user logged in, routing to / todo.');
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
    )
  }

}
