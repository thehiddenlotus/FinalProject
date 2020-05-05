import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { SearchComponent } from './components/search/search.component';
import { LocationDetailComponent } from './components/location-detail/location-detail.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';

import { TeamComponent } from './components/team/team.component';

import { ChangeAddressComponent } from './components/change-address/change-address.component';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'search', component: SearchComponent },
  { path: 'search/:searchKey', component: SearchComponent },
  { path: 'locations/:id', component: LocationDetailComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'settings', component: UserSettingsComponent },

  { path: 'team', component: TeamComponent},

  { path: 'changeAddress', component: ChangeAddressComponent }

  // { path: '**', component: NotFoundComponent }
] ;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
