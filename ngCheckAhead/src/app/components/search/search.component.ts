import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Location } from 'src/app/models/location';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  currentUser: User = null;
  newLocation: Location = null;
  userId = null;

  constructor(
    private auth: AuthService,
    private userSvc: UserService
  ) { }

  ngOnInit(): void {
    this.userId = this.auth.getCurrentUserId();

    this.userSvc.show(this.userId).subscribe(
      success => {
        this.currentUser = success;
        console.log(this.currentUser);

      },
      fail => {
        console.log("no user logged in");

      }
    )
  }

  addLocation() {
    this.newLocation = new Location();
    this.newLocation.creator = this.currentUser;
  }

}
