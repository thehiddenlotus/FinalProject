import { UserService } from './services/user.service';
import { ReviewService } from './services/review.service';
import { ReviewRatingService } from './services/review-rating.service';
import { ReviewCommentService } from './services/review-comment.service';
import { RatingService } from './services/rating.service';
import { AddressService } from './services/address.service';
import { LocationService } from './services/location.service';
import { AuthService } from './services/auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { LocationDetailComponent } from './components/location-detail/location-detail.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { TeamComponent } from './components/team/team.component';

import { FormsModule } from '@angular/forms';
import { LogoutComponent } from './components/logout/logout.component';
import { ReviewComponent } from './components/review/review.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import { CommentComponent } from './components/comment/comment.component';
import { CommentFormComponent } from './components/comment-form/comment-form.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchComponent,
    LocationDetailComponent,
    UserSettingsComponent,
    LoginComponent,
    RegisterComponent,
    NavBarComponent,
    FooterComponent,
    TeamComponent,
    LogoutComponent,
    ReviewComponent,
    ReviewFormComponent,
    CommentComponent,
    CommentFormComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthService,
    LocationService,
    AddressService,
    RatingService,
    ReviewCommentService,
    ReviewRatingService,
    ReviewService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
