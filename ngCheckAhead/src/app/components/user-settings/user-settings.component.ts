import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css']
})
export class UserSettingsComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router,
    private userSvc: UserService
  ) { }

  ngOnInit(): void {
    const userId = this.auth.getCurrentUserId();
    console.log("Current user id is " + userId);

  }

  checkLogin(): boolean {
    return this.auth.checkLogin();
  }

  deleteUser() {
    const userIdStr = this.auth.getCurrentUserId();
    const userId = Number.parseInt(userIdStr);
    console.log(userId);

    this.userSvc.destroy(userId).subscribe(
      success => {
        this.router.navigateByUrl('/home');
      },
      fail => {
        console.error("This user failed to delete");
        console.error(fail);

      }
    )

  }

}
