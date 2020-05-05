import { Component, OnInit, OnChanges } from '@angular/core';
import { User } from 'src/app/models/user';
import { Location } from 'src/app/models/location';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from "@angular/router";


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  
  urlParam = this.route.snapshot.paramMap.get('searchKey');
  aKey: string = this.urlParam;

  searchKey: string = null;
  
  currentUser: User = null;
  newLocation: Location = null;
  userId = null;

  constructor(
    private auth: AuthService,
    private userSvc: UserService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.setSearchParamIfExists();
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

  setSearchParamIfExists(){
    if(this.route.snapshot.paramMap.has){
      this.searchKey = this.aKey
    }
    else{
      this.searchKey = null
    }
  }


  addLocation() {
    this.newLocation = new Location();
    this.newLocation.creator = this.currentUser;
  }


}
