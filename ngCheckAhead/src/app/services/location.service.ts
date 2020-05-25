import { PopulartimesData } from './../models/populartimes-data';
import { Injectable, Optional } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Location } from '../models/location';
import { AuthService } from './auth.service';
import { PlaceId } from '../models/place-id';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  [x: string]: any;
  private url = environment.baseUrl + 'api/locations'
  private locations : Location [] = [];
  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }
  public index() {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Location[]>(this.url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.index: error retrieving location: ' + err);
      })
    );
  }

  public show(id) {
    const httpOptions = this.getHttpOptions();
    return this.http.get<Location>(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.show: error retrieving entry: ' + err);
      })
    );
  }
  public create(location: Location) {
   this.locations.push(location);
    const httpOptions = this.getHttpOptions();
    return this.http.post<Location>(this.url, location, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.create: error creating entry: ' + err);
      })
    );
  }
  public update(location: Location) {
    const httpOptions = this.getHttpOptions();
    return this.http
      .put<Location>(`${this.url}/${location.id}`, location, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('LocationService.update: error updating todo: ' + err);
        })
      );
  }
  public destroy(id: number) {
    const httpOptions = this.getHttpOptions();
    return this.http.delete(`${this.url}/${id}`, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('LocationService.delete: error deleting entry: ' + err);
      })
    );
  }
  public getId(location: Location) {
    var httpOptions = this.getHttpOptions();
    const proxyurl = "https://cors-anywhere.herokuapp.com/";
    const name = location.name.trim().split(' ');
    const words = location.address.address.split(' ');
    const zip = location.address.zip;
    var url = 'https://maps.googleapis.com/maps/api/place/textsearch/json?query=';
    // name.forEach(element => {
    //   url += '+'+element;
    // });
    words.forEach(element => {
      if (url.slice(-1) !== '=') {
        url += '+'+element;
      }else{
        url += element;
      }
    });
    url += '+'+zip;
    url += '&key=AIzaSyCg68MnHawVIaCoIlGekODBeGEY13YWjxM';
    console.log(url);

    return this.http.get<PlaceId>(proxyurl+url, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error getting  transit data from api: ' + err);
      })
    );
  }
  private getHttpOptions() {
    const credentials = this.auth.getCredentials();
    let httpOptions = {};
    if (credentials) {

      httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-requested-with': 'XMLHttpRequest',
          'Authorization': `Basic ${credentials}`,
          'Access-Control-Allow-Origin': '*'
        })
      };
    } else {
      httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'x-requested-with': 'XMLHttpRequest'
        })
      };
    }
    return httpOptions;
  }

}



