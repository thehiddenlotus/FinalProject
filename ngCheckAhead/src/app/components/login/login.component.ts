import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  accessDenied = false;
  constructor(
    private router: Router,
    private auth: AuthService
  ) { }

  ngOnInit(): void {
  }

  login(form: NgForm) {
    const user = form.value;
    this.auth.login(user.username, user.password).subscribe(
      success => {
        console.log('LoginComponent.login(): Logged in');

        this.router.navigate(['/search']);
      },
      failed => {
        console.log('LoginComponent.login(): Login Failed');
        this.accessDenied = true;
        this.router.navigateByUrl('/login');
      }
    );
  }

}
